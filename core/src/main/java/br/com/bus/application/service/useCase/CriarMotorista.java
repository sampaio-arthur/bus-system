package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.MotoristaDTO;
import br.com.bus.application.mapper.MotoristaMap;
import br.com.bus.domain.Motorista;
import br.com.bus.repository.MotoristaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarMotorista {

    @Inject
    MotoristaRepository repository;

    @Transactional
    public MotoristaDTO executar(MotoristaDTO dto) {
        Motorista entity = MotoristaMap.toEntity(dto);
        repository.persist(entity);
        return MotoristaMap.toDTO(entity);
    }
}

