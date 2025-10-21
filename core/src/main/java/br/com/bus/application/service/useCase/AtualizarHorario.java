package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.HorarioDTO;
import br.com.bus.application.mapper.HorarioMap;
import br.com.bus.domain.Horario;
import br.com.bus.repository.HorarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarHorario {

    @Inject
    HorarioRepository repository;

    @Transactional
    public HorarioDTO executar(Long id, HorarioDTO dto) {
        Horario entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Horario não encontrado: id=" + id));
        HorarioMap.updateEntityFromDTO(dto, entity);
        return HorarioMap.toDTO(entity);
    }
}

