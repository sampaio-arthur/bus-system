package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PessoaDTO;
import br.com.bus.application.mapper.PessoaMap;
import br.com.bus.domain.Pessoa;
import br.com.bus.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPessoa {

    @Inject
    PessoaRepository repository;

    @Transactional
    public PessoaDTO executar(PessoaDTO dto) {
        Pessoa entity = new Pessoa();
        PessoaMap.updateEntityFromDTO(dto, entity);
        repository.persist(entity);
        return PessoaMap.toDTO(entity);
    }
}
