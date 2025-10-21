package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.VanDTO;
import br.com.bus.application.mapper.VanMap;
import br.com.bus.domain.Van;
import br.com.bus.repository.VanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarVan {

    @Inject
    VanRepository repository;

    @Transactional
    public VanDTO executar(Long id, VanDTO dto) {
        Van entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Van não encontrada: id=" + id));
        VanMap.updateEntityFromDTO(dto, entity);
        return VanMap.toDTO(entity);
    }
}

