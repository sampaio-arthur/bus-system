package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.LinhaDTO;
import br.com.bus.application.mapper.LinhaMap;
import br.com.bus.domain.Linha;
import br.com.bus.repository.LinhaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaLinha {

    @Inject
    LinhaRepository repository;

    public LinhaDTO porId(Long id) {
        Linha entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Linha não encontrada: id=" + id));
        return LinhaMap.toDTO(entity);
    }

    public List<LinhaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(LinhaMap::toSummary)
                .collect(Collectors.toList());
    }
}

