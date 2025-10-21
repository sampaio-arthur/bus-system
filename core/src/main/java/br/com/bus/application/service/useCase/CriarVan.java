package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.VanDTO;
import br.com.bus.application.mapper.VanMap;
import br.com.bus.domain.Van;
import br.com.bus.repository.VanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarVan {

    @Inject
    VanRepository repository;

    @Transactional
    public VanDTO executar(VanDTO dto) {
        Van entity = VanMap.toEntity(dto);
        repository.persist(entity);
        return VanMap.toDTO(entity);
    }
}

