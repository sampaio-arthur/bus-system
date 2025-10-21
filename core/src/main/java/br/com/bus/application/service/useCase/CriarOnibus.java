package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.OnibusDTO;
import br.com.bus.application.mapper.OnibusMap;
import br.com.bus.domain.Onibus;
import br.com.bus.repository.OnibusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarOnibus {

    @Inject
    OnibusRepository repository;

    @Transactional
    public OnibusDTO executar(OnibusDTO dto) {
        Onibus entity = OnibusMap.toEntity(dto);
        repository.persist(entity);
        return OnibusMap.toDTO(entity);
    }
}

