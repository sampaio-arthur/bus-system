package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PecaDTO;
import br.com.bus.application.mapper.PecaMap;
import br.com.bus.repository.PecaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaPeca {

    @Inject
    PecaRepository repository;

    public PecaDTO porId(Long id) {
        return PecaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Peça não encontrada: id=" + id))
        );
    }

    public List<PecaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(PecaMap::toSummary)
                .collect(Collectors.toList());
    }
}
