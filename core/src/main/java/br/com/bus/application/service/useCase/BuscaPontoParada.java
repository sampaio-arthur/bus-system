package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.mapper.PontoParadaMap;
import br.com.bus.repository.PontoParadaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaPontoParada {

    @Inject
    PontoParadaRepository repository;

    public PontoParadaDTO porId(Long id) {
        return PontoParadaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("PontoParada não encontrado: id=" + id))
        );
    }

    public List<PontoParadaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(PontoParadaMap::toSummary)
                .collect(Collectors.toList());
    }
}

