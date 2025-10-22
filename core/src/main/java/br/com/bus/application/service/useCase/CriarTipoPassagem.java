package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.TipoPassagemDTO;
import br.com.bus.application.mapper.TipoPassagemMap;
import br.com.bus.domain.TipoPassagem;
import br.com.bus.repository.TipoPassagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarTipoPassagem {

    @Inject
    TipoPassagemRepository repository;

    @Transactional
    public TipoPassagemDTO executar(TipoPassagemDTO dto) {
        TipoPassagem entity = TipoPassagemMap.toEntity(dto);
        repository.persist(entity);
        return TipoPassagemMap.toDTO(entity);
    }
}
