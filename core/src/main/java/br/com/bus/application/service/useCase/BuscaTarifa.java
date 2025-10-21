package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.TarifaDTO;
import br.com.bus.application.mapper.TarifaMap;
import br.com.bus.repository.TarifaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaTarifa {

    @Inject
    TarifaRepository repository;

    public TarifaDTO porId(Long id) {
        return TarifaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Tarifa não encontrada: id=" + id))
        );
    }

    public List<TarifaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(TarifaMap::toSummary)
                .collect(Collectors.toList());
    }
}

