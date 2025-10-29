package br.com.bus.application.service.useCase;

import br.com.bus.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarPessoa {

    @Inject
    PessoaRepository repository;

    @Transactional
    public void executar(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Pessoa não encontrada: id=" + id);
        }
    }
}
