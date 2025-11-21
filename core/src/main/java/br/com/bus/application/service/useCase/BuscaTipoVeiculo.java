package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.TipoVeiculoDTO;
import br.com.bus.application.mapper.TipoVeiculoMap;
import br.com.bus.repository.TipoVeiculoRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaTipoVeiculo {

    @Inject
    TipoVeiculoRepository repository;

    public TipoVeiculoDTO porId(Long id) {
        return TipoVeiculoMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("TipoVeiculo não encontrado: id=" + id))
        );
    }

    public List<TipoVeiculoDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(TipoVeiculoMap::toDTO)
                .collect(Collectors.toList());
    }
}

