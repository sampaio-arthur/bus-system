package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.LinhaDTO;
import br.com.bus.application.mapper.LinhaMap;
import br.com.bus.domain.Linha;
import br.com.bus.repository.LinhaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarLinha {

    @Inject
    LinhaRepository repository;

    @Transactional
    public LinhaDTO executar(LinhaDTO dto) {
        Linha entity = LinhaMap.toEntity(dto);
        repository.persist(entity);
        return LinhaMap.toDTO(entity);
    }
}

