package br.com.bus.application.service.useCase.bot;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.mapper.PontoTuristicoMap;
import br.com.bus.domain.PontoTuristico;
import br.com.bus.repository.PontoTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarPontosTuristicosPorPontoParadaECidade {

    @Inject
    PontoTuristicoRepository pontoTuristicoRepository;

    public List<PontoTuristicoDTO> executar(Long idPontoParada, String nomeCidade) {
        String cidade = nomeCidade == null ? "" : nomeCidade.toLowerCase();
        List<PontoTuristico> pontos = pontoTuristicoRepository.find(
                "select distinct pt from PontoTuristico pt " +
                        "join pt.pontosParadaProximos pp " +
                        "join pp.cidade c " +
                        "where pp.id = ?1 and lower(c.nome) = ?2",
                idPontoParada, cidade)
            .list();

        return pontos.stream()
                .map(PontoTuristicoMap::toSummary)
                .collect(Collectors.toList());
    }
}

