package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.PessoaDTO;
import br.com.bus.application.service.useCase.AtualizarPessoa;
import br.com.bus.application.service.useCase.BuscaPessoa;
import br.com.bus.application.service.useCase.CriarPessoa;
import br.com.bus.application.service.useCase.DeletarPessoa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PessoaService {

    @Inject
    BuscaPessoa busca;

    @Inject
    CriarPessoa criar;

    @Inject
    AtualizarPessoa atualizar;

    @Inject
    DeletarPessoa deletar;

    public List<PessoaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public PessoaDTO buscarPorId(Long id) { return busca.porId(id); }
    public PessoaDTO criar(PessoaDTO dto) { return criar.executar(dto); }
    public PessoaDTO atualizar(Long id, PessoaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
