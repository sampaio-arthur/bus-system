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
public class BuscarPontosParadaPorPontoTuristicoNomeECidade {

    @Inject
    PontoParadaRepository pontoParadaRepository;

    public List<PontoParadaDTO> executar(String nomePontoTuristico, String nomeCidade) {
        String cidade = nomeCidade == null ? "" : nomeCidade.toLowerCase();
        String nome = nomePontoTuristico == null ? "" : nomePontoTuristico.toLowerCase();

        List<PontoParada> pontos = pontoParadaRepository.find(
                "select distinct pp from PontoParada pp " +
                        "join pp.pontosTuristicosProximos pt " +
                        "join pp.cidade c " +
                        "where lower(pt.nome) = ?1 and lower(c.nome) = ?2",
                nome, cidade)
            .list();

        return pontos.stream()
                .map(PontoParadaMap::toSummary)
                .collect(Collectors.toList());
    }
}

