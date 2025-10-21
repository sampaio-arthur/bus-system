package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.RotaDTO;
import br.com.bus.application.mapper.RotaMap;
import br.com.bus.repository.RotaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaRota {

    @Inject
    RotaRepository repository;

    public RotaDTO porId(Long id) {
        return RotaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Rota não encontrada: id=" + id))
        );
    }

    public List<RotaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(RotaMap::toSummary)
                .collect(Collectors.toList());
    }
}

