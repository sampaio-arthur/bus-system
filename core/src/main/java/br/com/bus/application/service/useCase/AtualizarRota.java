package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.RotaDTO;
import br.com.bus.application.mapper.RotaMap;
import br.com.bus.domain.Rota;
import br.com.bus.repository.RotaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarRota {

    @Inject
    RotaRepository repository;

    @Transactional
    public RotaDTO executar(Long id, RotaDTO dto) {
        Rota entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Rota não encontrada: id=" + id));
        RotaMap.updateEntityFromDTO(dto, entity);
        return RotaMap.toDTO(entity);
    }
}

