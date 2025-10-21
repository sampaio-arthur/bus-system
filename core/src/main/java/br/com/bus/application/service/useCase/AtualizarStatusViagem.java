package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.StatusViagemDTO;
import br.com.bus.application.mapper.StatusViagemMap;
import br.com.bus.domain.StatusViagem;
import br.com.bus.repository.StatusViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarStatusViagem {

    @Inject
    StatusViagemRepository repository;

    @Transactional
    public StatusViagemDTO executar(Long id, StatusViagemDTO dto) {
        StatusViagem entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("StatusViagem não encontrado: id=" + id));
        StatusViagemMap.updateEntityFromDTO(dto, entity);
        return StatusViagemMap.toDTO(entity);
    }
}

