package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.PecaDTO;
import br.com.bus.application.service.useCase.AtualizarPeca;
import br.com.bus.application.service.useCase.BuscaPeca;
import br.com.bus.application.service.useCase.CriarPeca;
import br.com.bus.application.service.useCase.DeletarPeca;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PecaService {

    @Inject
    BuscaPeca busca;

    @Inject
    CriarPeca criar;

    @Inject
    AtualizarPeca atualizar;

    @Inject
    DeletarPeca deletar;

    public List<PecaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public PecaDTO buscarPorId(Long id) { return busca.porId(id); }
    public PecaDTO criar(PecaDTO dto) { return criar.executar(dto); }
    public PecaDTO atualizar(Long id, PecaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
