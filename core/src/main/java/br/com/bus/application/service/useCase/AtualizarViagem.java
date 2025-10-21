package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.mapper.ViagemMap;
import br.com.bus.domain.Viagem;
import br.com.bus.repository.ViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarViagem {

    @Inject
    ViagemRepository repository;

    @Transactional
    public ViagemDTO executar(Long id, ViagemDTO dto) {
        Viagem entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Viagem não encontrada: id=" + id));
        ViagemMap.updateEntityFromDTO(dto, entity);
        return ViagemMap.toDTO(entity);
    }
}

