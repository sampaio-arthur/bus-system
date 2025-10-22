package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PecaDTO;
import br.com.bus.application.mapper.PecaMap;
import br.com.bus.domain.Peca;
import br.com.bus.repository.PecaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPeca {

    @Inject
    PecaRepository repository;

    @Transactional
    public PecaDTO executar(PecaDTO dto) {
        Peca entity = PecaMap.toEntity(dto);
        repository.persist(entity);
        return PecaMap.toDTO(entity);
    }
}
