package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.FeedbackPassageiroDTO;
import br.com.bus.application.mapper.FeedbackPassageiroMap;
import br.com.bus.domain.FeedbackPassageiro;
import br.com.bus.repository.FeedbackPassageiroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarFeedbackPassageiro {

    @Inject
    FeedbackPassageiroRepository repository;

    @Transactional
    public FeedbackPassageiroDTO executar(Long id, FeedbackPassageiroDTO dto) {
        FeedbackPassageiro entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("FeedbackPassageiro não encontrado: id=" + id));
        FeedbackPassageiroMap.updateEntityFromDTO(dto, entity);
        return FeedbackPassageiroMap.toDTO(entity);
    }
}

