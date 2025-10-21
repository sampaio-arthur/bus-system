package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.application.service.useCase.AtualizarPassagem;
import br.com.bus.application.service.useCase.BuscaPassagem;
import br.com.bus.application.service.useCase.CriarPassagem;
import br.com.bus.application.service.useCase.DeletarPassagem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PassagemService {

    @Inject
    BuscaPassagem busca;

    @Inject
    CriarPassagem criar;

    @Inject
    AtualizarPassagem atualizar;

    @Inject
    DeletarPassagem deletar;

    public List<PassagemDTO> listar(int page, int size) { return busca.listar(page, size); }
    public PassagemDTO buscarPorId(Long id) { return busca.porId(id); }
    public PassagemDTO criar(PassagemDTO dto) { return criar.executar(dto); }
    public PassagemDTO atualizar(Long id, PassagemDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

