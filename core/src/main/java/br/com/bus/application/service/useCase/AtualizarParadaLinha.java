package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ParadaLinhaDTO;
import br.com.bus.application.mapper.ParadaLinhaMap;
import br.com.bus.domain.ParadaLinha;
import br.com.bus.repository.ParadaLinhaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarParadaLinha {

    @Inject
    ParadaLinhaRepository repository;

    @Transactional
    public ParadaLinhaDTO executar(Long id, ParadaLinhaDTO dto) {
        ParadaLinha entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("ParadaLinha não encontrada: id=" + id));
        ParadaLinhaMap.updateEntityFromDTO(dto, entity);
        return ParadaLinhaMap.toDTO(entity);
    }
}

