package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PassageiroDTO;
import br.com.bus.application.mapper.PassageiroMap;
import br.com.bus.domain.Passageiro;
import br.com.bus.repository.PassageiroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPassageiro {

    @Inject
    PassageiroRepository repository;

    @Transactional
    public PassageiroDTO executar(PassageiroDTO dto) {
        Passageiro entity = PassageiroMap.toEntity(dto);
        repository.persist(entity);
        return PassageiroMap.toDTO(entity);
    }
}

