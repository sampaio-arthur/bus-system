package br.com.bus.application.service.useCase;

import br.com.bus.repository.MotoristaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarMotorista {

    @Inject
    MotoristaRepository repository;

    @Transactional
    public void executar(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Motorista não encontrado: id=" + id);
        }
    }
}

