package br.com.bus.application.service.useCase;

import br.com.bus.repository.PassagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarPassagem {

    @Inject
    PassagemRepository repository;

    @Transactional
    public void executar(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Passagem não encontrada: id=" + id);
        }
    }
}

