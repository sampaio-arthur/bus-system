package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.MotoristaDTO;
import br.com.bus.application.mapper.MotoristaMap;
import br.com.bus.domain.Motorista;
import br.com.bus.repository.MotoristaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarMotorista {

    @Inject
    MotoristaRepository repository;

    @Transactional
    public MotoristaDTO executar(Long id, MotoristaDTO dto) {
        Motorista entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Motorista não encontrado: id=" + id));
        MotoristaMap.updateEntityFromDTO(dto, entity);
        return MotoristaMap.toDTO(entity);
    }
}

