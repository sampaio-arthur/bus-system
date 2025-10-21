package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.VanDTO;
import br.com.bus.application.service.useCase.AtualizarVan;
import br.com.bus.application.service.useCase.BuscaVan;
import br.com.bus.application.service.useCase.CriarVan;
import br.com.bus.application.service.useCase.DeletarVan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VanService {

    @Inject
    BuscaVan busca;

    @Inject
    CriarVan criar;

    @Inject
    AtualizarVan atualizar;

    @Inject
    DeletarVan deletar;

    public List<VanDTO> listar(int page, int size) { return busca.listar(page, size); }
    public VanDTO buscarPorId(Long id) { return busca.porId(id); }
    public VanDTO criar(VanDTO dto) { return criar.executar(dto); }
    public VanDTO atualizar(Long id, VanDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

