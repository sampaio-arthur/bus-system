package br.com.bus.application.service.useCase;

import br.com.bus.repository.TipoVeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarTipoVeiculo {

    @Inject
    TipoVeiculoRepository repository;

    @Transactional
    public void executar(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("TipoVeiculo não encontrado: id=" + id);
        }
    }
}

