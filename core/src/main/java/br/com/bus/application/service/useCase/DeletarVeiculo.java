package br.com.bus.application.service.useCase;

import br.com.bus.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarVeiculo {

    @Inject
    VeiculoRepository repository;

    @Transactional
    public void executar(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Veículo não encontrado: id=" + id);
        }
    }
}
