package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.OnibusDTO;
import br.com.bus.application.mapper.OnibusMap;
import br.com.bus.repository.OnibusRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaOnibus {

    @Inject
    OnibusRepository repository;

    public OnibusDTO porId(Long id) {
        return OnibusMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Onibus não encontrado: id=" + id))
        );
    }

    public List<OnibusDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(OnibusMap::toSummary)
                .collect(Collectors.toList());
    }
}

