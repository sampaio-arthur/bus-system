package br.com.bus.application.service.useCase.bot;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.mapper.PontoParadaMap;
import br.com.bus.domain.PontoParada;
import br.com.bus.repository.PontoParadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarPontosParadaPorPontoTuristicoECidade {

    @Inject
    PontoParadaRepository pontoParadaRepository;

    public List<PontoParadaDTO> executar(Long idPontoTuristico, String nomeCidade) {
        String cidade = nomeCidade == null ? "" : nomeCidade.toLowerCase();
        List<PontoParada> pontos = pontoParadaRepository.find(
                "select distinct pp from PontoParada pp " +
                        "join pp.pontosTuristicosProximos pt " +
                        "join pp.cidade c " +
                        "where pt.id = ?1 and lower(c.nome) = ?2",
                idPontoTuristico, cidade)
            .list();

        return pontos.stream()
                .map(PontoParadaMap::toSummary)
                .collect(Collectors.toList());
    }
}

