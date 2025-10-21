package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ParadaLinhaDTO;
import br.com.bus.application.mapper.ParadaLinhaMap;
import br.com.bus.domain.ParadaLinha;
import br.com.bus.repository.ParadaLinhaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarParadaLinha {

    @Inject
    ParadaLinhaRepository repository;

    @Transactional
    public ParadaLinhaDTO executar(ParadaLinhaDTO dto) {
        ParadaLinha entity = ParadaLinhaMap.toEntity(dto);
        repository.persist(entity);
        return ParadaLinhaMap.toDTO(entity);
    }
}

