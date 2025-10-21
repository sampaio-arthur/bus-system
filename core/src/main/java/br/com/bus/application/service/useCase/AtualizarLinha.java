package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.LinhaDTO;
import br.com.bus.application.mapper.LinhaMap;
import br.com.bus.domain.Linha;
import br.com.bus.repository.LinhaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarLinha {

    @Inject
    LinhaRepository repository;

    @Transactional
    public LinhaDTO executar(Long id, LinhaDTO dto) {
        Linha entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Linha não encontrada: id=" + id));
        LinhaMap.updateEntityFromDTO(dto, entity);
        return LinhaMap.toDTO(entity);
    }
}

