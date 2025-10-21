package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.HorarioDTO;
import br.com.bus.application.mapper.HorarioMap;
import br.com.bus.domain.Horario;
import br.com.bus.repository.HorarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarHorario {

    @Inject
    HorarioRepository repository;

    @Transactional
    public HorarioDTO executar(HorarioDTO dto) {
        Horario entity = HorarioMap.toEntity(dto);
        repository.persist(entity);
        return HorarioMap.toDTO(entity);
    }
}

