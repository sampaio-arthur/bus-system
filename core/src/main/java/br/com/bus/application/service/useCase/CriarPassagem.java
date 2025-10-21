package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.application.mapper.PassagemMap;
import br.com.bus.domain.Passagem;
import br.com.bus.repository.PassagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPassagem {

    @Inject
    PassagemRepository repository;

    @Transactional
    public PassagemDTO executar(PassagemDTO dto) {
        Passagem entity = PassagemMap.toEntity(dto);
        repository.persist(entity);
        return PassagemMap.toDTO(entity);
    }
}

