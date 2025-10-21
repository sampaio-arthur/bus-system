package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.mapper.ViagemMap;
import br.com.bus.repository.ViagemRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaViagem {

    @Inject
    ViagemRepository repository;

    public ViagemDTO porId(Long id) {
        return ViagemMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Viagem não encontrada: id=" + id))
        );
    }

    public List<ViagemDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(ViagemMap::toSummary)
                .collect(Collectors.toList());
    }
}

