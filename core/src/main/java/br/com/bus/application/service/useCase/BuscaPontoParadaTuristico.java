package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaTuristicoDTO;
import br.com.bus.application.mapper.PontoParadaTuristicoMap;
import br.com.bus.repository.PontoParadaTuristicoRepository;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristicoId;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaPontoParadaTuristico {

    @Inject
    PontoParadaTuristicoRepository repository;

    public PontoParadaTuristicoDTO porId(Long idPontoParada, Long idPontoTuristico) {
        PontoParadaTuristicoId id = new PontoParadaTuristicoId(idPontoParada, idPontoTuristico);
        return PontoParadaTuristicoMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Ponto parada turístico não encontrado: parada=" + idPontoParada + ", turistico=" + idPontoTuristico))
        );
    }

    public List<PontoParadaTuristicoDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(PontoParadaTuristicoMap::toDTO)
                .collect(Collectors.toList());
    }
}
