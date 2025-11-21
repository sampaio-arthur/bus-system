package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.application.mapper.VeiculoMap;
import br.com.bus.repository.VeiculoRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaVeiculo {

    @Inject
    VeiculoRepository repository;

    public VeiculoDTO porId(Long id) {
        return VeiculoMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Veículo não encontrado: id=" + id))
        );
    }

    public List<VeiculoDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(VeiculoMap::toDTO)
                .collect(Collectors.toList());
    }
}
