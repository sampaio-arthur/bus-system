package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.FeedbackPassageiroDTO;
import br.com.bus.application.mapper.FeedbackPassageiroMap;
import br.com.bus.repository.FeedbackPassageiroRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaFeedbackPassageiro {

    @Inject
    FeedbackPassageiroRepository repository;

    public FeedbackPassageiroDTO porId(Long id) {
        return FeedbackPassageiroMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("FeedbackPassageiro não encontrado: id=" + id))
        );
    }

    public List<FeedbackPassageiroDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(FeedbackPassageiroMap::toSummary)
                .collect(Collectors.toList());
    }
}

