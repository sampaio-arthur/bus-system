package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PassageiroDTO;
import br.com.bus.application.mapper.PassageiroMap;
import br.com.bus.repository.PassageiroRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaPassageiro {

    @Inject
    PassageiroRepository repository;

    public PassageiroDTO porId(Long id) {
        return PassageiroMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Passageiro não encontrado: id=" + id))
        );
    }

    public List<PassageiroDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(PassageiroMap::toSummary)
                .collect(Collectors.toList());
    }
}

