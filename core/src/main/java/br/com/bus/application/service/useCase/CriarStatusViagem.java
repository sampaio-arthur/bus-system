package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.StatusViagemDTO;
import br.com.bus.application.mapper.StatusViagemMap;
import br.com.bus.domain.StatusViagem;
import br.com.bus.repository.StatusViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarStatusViagem {

    @Inject
    StatusViagemRepository repository;

    @Transactional
    public StatusViagemDTO executar(StatusViagemDTO dto) {
        StatusViagem entity = StatusViagemMap.toEntity(dto);
        repository.persist(entity);
        return StatusViagemMap.toDTO(entity);
    }
}

