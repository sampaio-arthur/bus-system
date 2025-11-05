package br.com.bus.application.service.useCase.bot;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.mapper.ViagemMap;
import br.com.bus.domain.Linha;
import br.com.bus.domain.Viagem;
import br.com.bus.repository.ItinerarioRepository;
import br.com.bus.repository.ViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarViagensPorCidade {

    @Inject
    ItinerarioRepository itinerarioRepository;

    @Inject
    ViagemRepository viagemRepository;

    public List<ViagemDTO> executar(String nomeCidade) {
        String cidade = nomeCidade == null ? "" : nomeCidade.toLowerCase();

        List<Linha> linhas = itinerarioRepository.getEntityManager()
                .createQuery(
                        "select distinct l from Itinerario i " +
                                "join i.linha l " +
                                "join i.pontoParada pp " +
                                "join pp.cidade c " +
                                "where lower(c.nome) = :cidade",
                        Linha.class)
                .setParameter("cidade", cidade)
                .getResultList();

        if (linhas.isEmpty()) {
            return Collections.emptyList();
        }

        List<Viagem> viagens = viagemRepository.find("linha in ?1", linhas).list();

        return viagens.stream()
                .map(ViagemMap::toSummary)
                .collect(Collectors.toList());
    }
}

