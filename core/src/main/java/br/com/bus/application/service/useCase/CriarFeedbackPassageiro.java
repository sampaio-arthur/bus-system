package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.FeedbackPassageiroDTO;
import br.com.bus.application.mapper.FeedbackPassageiroMap;
import br.com.bus.domain.FeedbackPassageiro;
import br.com.bus.repository.FeedbackPassageiroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarFeedbackPassageiro {

    @Inject
    FeedbackPassageiroRepository repository;

    @Transactional
    public FeedbackPassageiroDTO executar(FeedbackPassageiroDTO dto) {
        FeedbackPassageiro entity = FeedbackPassageiroMap.toEntity(dto);
        repository.persist(entity);
        return FeedbackPassageiroMap.toDTO(entity);
    }
}

