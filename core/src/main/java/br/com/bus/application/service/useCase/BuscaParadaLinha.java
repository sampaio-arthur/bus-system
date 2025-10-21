package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ParadaLinhaDTO;
import br.com.bus.application.mapper.ParadaLinhaMap;
import br.com.bus.repository.ParadaLinhaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaParadaLinha {

    @Inject
    ParadaLinhaRepository repository;

    public ParadaLinhaDTO porId(Long id) {
        return ParadaLinhaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("ParadaLinha não encontrada: id=" + id))
        );
    }

    public List<ParadaLinhaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(ParadaLinhaMap::toSummary)
                .collect(Collectors.toList());
    }
}

