package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.MotoristaDTO;
import br.com.bus.application.mapper.MotoristaMap;
import br.com.bus.repository.MotoristaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaMotorista {

    @Inject
    MotoristaRepository repository;

    public MotoristaDTO porId(Long id) {
        return MotoristaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Motorista não encontrado: id=" + id))
        );
    }

    public List<MotoristaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(MotoristaMap::toSummary)
                .collect(Collectors.toList());
    }
}

