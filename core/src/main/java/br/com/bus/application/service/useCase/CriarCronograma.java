package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.CronogramaDTO;
import br.com.bus.application.mapper.CronogramaMap;
import br.com.bus.domain.Cronograma;
import br.com.bus.repository.CronogramaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarCronograma {

    @Inject
    CronogramaRepository repository;

    @Transactional
    public CronogramaDTO executar(CronogramaDTO dto) {
        Cronograma entity = CronogramaMap.toEntity(dto);
        repository.persist(entity);
        return CronogramaMap.toDTO(entity);
    }
}
