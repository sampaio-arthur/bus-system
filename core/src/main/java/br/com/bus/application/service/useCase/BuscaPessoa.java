package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PessoaDTO;
import br.com.bus.application.mapper.PessoaMap;
import br.com.bus.repository.PessoaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaPessoa {

    @Inject
    PessoaRepository repository;

    public PessoaDTO porId(Long id) {
        return PessoaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Pessoa não encontrada: id=" + id))
        );
    }

    public List<PessoaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(PessoaMap::toDTO)
                .collect(Collectors.toList());
    }
}
