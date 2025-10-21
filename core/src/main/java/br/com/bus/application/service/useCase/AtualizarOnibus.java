package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.OnibusDTO;
import br.com.bus.application.mapper.OnibusMap;
import br.com.bus.domain.Onibus;
import br.com.bus.repository.OnibusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarOnibus {

    @Inject
    OnibusRepository repository;

    @Transactional
    public OnibusDTO executar(Long id, OnibusDTO dto) {
        Onibus entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Onibus não encontrado: id=" + id));
        OnibusMap.updateEntityFromDTO(dto, entity);
        return OnibusMap.toDTO(entity);
    }
}

