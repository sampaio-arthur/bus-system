package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.TarifaDTO;
import br.com.bus.application.mapper.TarifaMap;
import br.com.bus.domain.Tarifa;
import br.com.bus.repository.TarifaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarTarifa {

    @Inject
    TarifaRepository repository;

    @Transactional
    public TarifaDTO executar(TarifaDTO dto) {
        Tarifa entity = TarifaMap.toEntity(dto);
        repository.persist(entity);
        return TarifaMap.toDTO(entity);
    }
}

