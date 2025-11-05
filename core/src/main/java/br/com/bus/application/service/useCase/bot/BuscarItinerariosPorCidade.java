package br.com.bus.application.service.useCase.bot;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.mapper.ItinerarioMap;
import br.com.bus.domain.itinerario.Itinerario;
import br.com.bus.repository.ItinerarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarItinerariosPorCidade {

    @Inject
    ItinerarioRepository itinerarioRepository;

    public List<ItinerarioDTO> executar(String nomeCidade) {
        String cidade = nomeCidade == null ? "" : nomeCidade.toLowerCase();
        List<Itinerario> itens = itinerarioRepository.find(
                "select i from Itinerario i " +
                        "join fetch i.linha l " +
                        "join fetch i.pontoParada pp " +
                        "join pp.cidade c " +
                        "where lower(c.nome) = ?1 " +
                        "order by l.id, i.id.ordem",
                cidade)
            .list();

        return itens.stream()
                .map(ItinerarioMap::toDTO)
                .collect(Collectors.toList());
    }
}

