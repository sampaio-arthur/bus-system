package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.RotaDTO;
import br.com.bus.application.mapper.RotaMap;
import br.com.bus.domain.Rota;
import br.com.bus.repository.RotaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarRota {

    @Inject
    RotaRepository repository;

    @Transactional
    public RotaDTO executar(RotaDTO dto) {
        Rota entity = RotaMap.toEntity(dto);
        repository.persist(entity);
        return RotaMap.toDTO(entity);
    }
}

