package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.service.useCase.AtualizarViagem;
import br.com.bus.application.service.useCase.BuscaViagem;
import br.com.bus.application.service.useCase.CriarViagem;
import br.com.bus.application.service.useCase.DeletarViagem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ViagemService {

    @Inject
    BuscaViagem busca;

    @Inject
    CriarViagem criar;

    @Inject
    AtualizarViagem atualizar;

    @Inject
    DeletarViagem deletar;

    public List<ViagemDTO> listar(int page, int size) { return busca.listar(page, size); }
    public ViagemDTO buscarPorId(Long id) { return busca.porId(id); }
    public ViagemDTO criar(ViagemDTO dto) { return criar.executar(dto); }
    public ViagemDTO atualizar(Long id, ViagemDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

