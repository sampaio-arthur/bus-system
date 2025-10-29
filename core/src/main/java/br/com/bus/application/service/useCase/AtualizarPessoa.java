package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PessoaDTO;
import br.com.bus.application.mapper.PessoaMap;
import br.com.bus.domain.Pessoa;
import br.com.bus.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPessoa {

    @Inject
    PessoaRepository repository;

    @Transactional
    public PessoaDTO executar(Long id, PessoaDTO dto) {
        Pessoa entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada: id=" + id));

        PessoaMap.updateEntityFromDTO(dto, entity);
        return PessoaMap.toDTO(entity);
    }
}
