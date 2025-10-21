package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.application.mapper.PassagemMap;
import br.com.bus.domain.Passagem;
import br.com.bus.repository.PassagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPassagem {

    @Inject
    PassagemRepository repository;

    @Transactional
    public PassagemDTO executar(Long id, PassagemDTO dto) {
        Passagem entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Passagem não encontrada: id=" + id));
        PassagemMap.updateEntityFromDTO(dto, entity);
        return PassagemMap.toDTO(entity);
    }
}

