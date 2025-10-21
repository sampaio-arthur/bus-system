package br.com.bus.application.service.useCase;

import br.com.bus.repository.FeedbackPassageiroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarFeedbackPassageiro {

    @Inject
    FeedbackPassageiroRepository repository;

    @Transactional
    public void executar(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("FeedbackPassageiro não encontrado: id=" + id);
        }
    }
}

