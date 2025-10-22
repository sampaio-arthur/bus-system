package br.com.bus.application.service.useCase;

import br.com.bus.repository.TipoPassagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarTipoPassagem {

    @Inject
    TipoPassagemRepository repository;

    @Transactional
    public void executar(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Tipo de passagem não encontrado: id=" + id);
        }
    }
}
