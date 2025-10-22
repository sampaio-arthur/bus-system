package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PecaDTO;
import br.com.bus.application.mapper.PecaMap;
import br.com.bus.domain.Peca;
import br.com.bus.repository.PecaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPeca {

    @Inject
    PecaRepository repository;

    @Transactional
    public PecaDTO executar(Long id, PecaDTO dto) {
        Peca entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Peça não encontrada: id=" + id));
        PecaMap.updateEntityFromDTO(dto, entity);
        return PecaMap.toDTO(entity);
    }
}
