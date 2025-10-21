package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.StatusViagemDTO;
import br.com.bus.application.mapper.StatusViagemMap;
import br.com.bus.repository.StatusViagemRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaStatusViagem {

    @Inject
    StatusViagemRepository repository;

    public StatusViagemDTO porId(Long id) {
        return StatusViagemMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("StatusViagem não encontrado: id=" + id))
        );
    }

    public List<StatusViagemDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(StatusViagemMap::toSummary)
                .collect(Collectors.toList());
    }
}

