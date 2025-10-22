package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.TipoPassagemDTO;
import br.com.bus.application.service.useCase.AtualizarTipoPassagem;
import br.com.bus.application.service.useCase.BuscaTipoPassagem;
import br.com.bus.application.service.useCase.CriarTipoPassagem;
import br.com.bus.application.service.useCase.DeletarTipoPassagem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TipoPassagemService {

    @Inject
    BuscaTipoPassagem busca;

    @Inject
    CriarTipoPassagem criar;

    @Inject
    AtualizarTipoPassagem atualizar;

    @Inject
    DeletarTipoPassagem deletar;

    public List<TipoPassagemDTO> listar(int page, int size) { return busca.listar(page, size); }
    public TipoPassagemDTO buscarPorId(Long id) { return busca.porId(id); }
    public TipoPassagemDTO criar(TipoPassagemDTO dto) { return criar.executar(dto); }
    public TipoPassagemDTO atualizar(Long id, TipoPassagemDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
