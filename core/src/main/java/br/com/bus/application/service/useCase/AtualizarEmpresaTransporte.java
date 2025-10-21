package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.EmpresaTransporteDTO;
import br.com.bus.application.mapper.EmpresaTransporteMap;
import br.com.bus.domain.EmpresaTransporte;
import br.com.bus.repository.EmpresaTransporteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarEmpresaTransporte {

    @Inject
    EmpresaTransporteRepository repository;

    @Transactional
    public EmpresaTransporteDTO executar(Long id, EmpresaTransporteDTO dto) {
        EmpresaTransporte entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("EmpresaTransporte não encontrada: id=" + id));
        EmpresaTransporteMap.updateEntityFromDTO(dto, entity);
        return EmpresaTransporteMap.toDTO(entity);
    }
}

