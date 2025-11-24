package br.com.bus.application.service.useCase.bot;

import java.util.NoSuchElementException;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.mapper.ProgressoViagemMap;
import br.com.bus.domain.progressoViagem.ProgressoViagem;
import br.com.bus.repository.ProgressoViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscarUltimoProgressoDaViagemPorCidade {

    @Inject
    ProgressoViagemRepository progressoViagemRepository;

    public ProgressoViagemDTO executar(Long idViagem, String nomeCidade) {
        String cidade = nomeCidade == null ? "" : nomeCidade.toLowerCase();

        ProgressoViagem pv = progressoViagemRepository.find(
                        "select pv from ProgressoViagem pv " +
                                "join fetch pv.pontoParada pp " +
                                "join pp.cidade c " +
                                "where pv.viagem.id = ?1 and lower(c.nome) = ?2 " +
                                "order by pv.id.data desc",
                        idViagem, cidade)
                .firstResultOptional()
                .orElseThrow(() -> new NotFoundException("Nenhum progresso encontrado para a viagem na cidade informada"));

        return ProgressoViagemMap.toDTO(pv);
    }
}

