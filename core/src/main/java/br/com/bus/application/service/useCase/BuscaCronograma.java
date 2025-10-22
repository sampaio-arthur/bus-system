package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.CronogramaDTO;
import br.com.bus.application.mapper.CronogramaMap;
import br.com.bus.repository.CronogramaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaCronograma {

    @Inject
    CronogramaRepository repository;

    public CronogramaDTO porId(Long id) {
        return CronogramaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Cronograma não encontrado: id=" + id))
        );
    }

    public List<CronogramaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(CronogramaMap::toSummary)
                .collect(Collectors.toList());
    }
}
