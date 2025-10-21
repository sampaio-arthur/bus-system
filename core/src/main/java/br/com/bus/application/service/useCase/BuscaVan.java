package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.VanDTO;
import br.com.bus.application.mapper.VanMap;
import br.com.bus.repository.VanRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaVan {

    @Inject
    VanRepository repository;

    public VanDTO porId(Long id) {
        return VanMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Van não encontrada: id=" + id))
        );
    }

    public List<VanDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(VanMap::toSummary)
                .collect(Collectors.toList());
    }
}

