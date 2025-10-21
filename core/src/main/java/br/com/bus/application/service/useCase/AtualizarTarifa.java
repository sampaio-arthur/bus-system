package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.TarifaDTO;
import br.com.bus.application.mapper.TarifaMap;
import br.com.bus.domain.Tarifa;
import br.com.bus.repository.TarifaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarTarifa {

    @Inject
    TarifaRepository repository;

    @Transactional
    public TarifaDTO executar(Long id, TarifaDTO dto) {
        Tarifa entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Tarifa não encontrada: id=" + id));
        TarifaMap.updateEntityFromDTO(dto, entity);
        return TarifaMap.toDTO(entity);
    }
}

