package br.com.bus.application.service.useCase;

import br.com.bus.repository.PontoParadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarPontoParada {

    @Inject
    PontoParadaRepository repository;

    @Transactional
    public void executar(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("PontoParada não encontrado: id=" + id);
        }
    }
}

