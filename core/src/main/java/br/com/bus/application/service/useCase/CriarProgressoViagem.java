package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.mapper.ProgressoViagemMap;
import br.com.bus.domain.progressoViagem.ProgressoViagem;
import br.com.bus.repository.ProgressoViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarProgressoViagem {

    @Inject
    ProgressoViagemRepository repository;

    @Transactional
    public ProgressoViagemDTO executar(ProgressoViagemDTO dto) {
        ProgressoViagem entity = ProgressoViagemMap.toEntity(dto);
        repository.persist(entity);
        return ProgressoViagemMap.toDTO(entity);
    }
}
