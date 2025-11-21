package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.TipoPassagemDTO;
import br.com.bus.application.mapper.TipoPassagemMap;
import br.com.bus.repository.TipoPassagemRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaTipoPassagem {

    @Inject
    TipoPassagemRepository repository;

    public TipoPassagemDTO porId(Long id) {
        return TipoPassagemMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Tipo de passagem não encontrado: id=" + id))
        );
    }

    public List<TipoPassagemDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(TipoPassagemMap::toDTO)
                .collect(Collectors.toList());
    }
}
