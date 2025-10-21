package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.mapper.ViagemMap;
import br.com.bus.domain.Viagem;
import br.com.bus.repository.ViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarViagem {

    @Inject
    ViagemRepository repository;

    @Transactional
    public ViagemDTO executar(ViagemDTO dto) {
        Viagem entity = ViagemMap.toEntity(dto);
        repository.persist(entity);
        return ViagemMap.toDTO(entity);
    }
}

