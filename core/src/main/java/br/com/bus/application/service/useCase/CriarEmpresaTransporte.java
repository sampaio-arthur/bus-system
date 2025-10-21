package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.EmpresaTransporteDTO;
import br.com.bus.application.mapper.EmpresaTransporteMap;
import br.com.bus.domain.EmpresaTransporte;
import br.com.bus.repository.EmpresaTransporteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarEmpresaTransporte {

    @Inject
    EmpresaTransporteRepository repository;

    @Transactional
    public EmpresaTransporteDTO executar(EmpresaTransporteDTO dto) {
        EmpresaTransporte entity = EmpresaTransporteMap.toEntity(dto);
        repository.persist(entity);
        return EmpresaTransporteMap.toDTO(entity);
    }
}

