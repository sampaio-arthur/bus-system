package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.CidadeDTO;
import br.com.bus.application.mapper.CidadeMap;
import br.com.bus.repository.CidadeRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaCidade {

    @Inject
    CidadeRepository repository;

    public CidadeDTO porId(Long id) {
        return CidadeMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Cidade não encontrada: id=" + id))
        );
    }

    public List<CidadeDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(CidadeMap::toSummary)
                .collect(Collectors.toList());
    }
}

