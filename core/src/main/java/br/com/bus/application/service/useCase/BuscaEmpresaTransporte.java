package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.EmpresaTransporteDTO;
import br.com.bus.application.mapper.EmpresaTransporteMap;
import br.com.bus.repository.EmpresaTransporteRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaEmpresaTransporte {

    @Inject
    EmpresaTransporteRepository repository;

    public EmpresaTransporteDTO porId(Long id) {
        return EmpresaTransporteMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("EmpresaTransporte não encontrada: id=" + id))
        );
    }

    public List<EmpresaTransporteDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(EmpresaTransporteMap::toSummary)
                .collect(Collectors.toList());
    }
}

