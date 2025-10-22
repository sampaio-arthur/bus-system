package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.CronogramaDTO;
import br.com.bus.application.mapper.CronogramaMap;
import br.com.bus.domain.Cronograma;
import br.com.bus.repository.CronogramaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarCronograma {

    @Inject
    CronogramaRepository repository;

    @Transactional
    public CronogramaDTO executar(Long id, CronogramaDTO dto) {
        Cronograma entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cronograma não encontrado: id=" + id));
        CronogramaMap.updateEntityFromDTO(dto, entity);
        return CronogramaMap.toDTO(entity);
    }
}
