package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.application.mapper.PassagemMap;
import br.com.bus.repository.PassagemRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaPassagem {

    @Inject
    PassagemRepository repository;

    public PassagemDTO porId(Long id) {
        return PassagemMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Passagem não encontrada: id=" + id))
        );
    }

    public List<PassagemDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(PassagemMap::toSummary)
                .collect(Collectors.toList());
    }
}

