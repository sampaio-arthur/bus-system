package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.application.service.useCase.AtualizarVeiculo;
import br.com.bus.application.service.useCase.BuscaVeiculo;
import br.com.bus.application.service.useCase.CriarVeiculo;
import br.com.bus.application.service.useCase.DeletarVeiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VeiculoService {

    @Inject
    BuscaVeiculo busca;

    @Inject
    CriarVeiculo criar;

    @Inject
    AtualizarVeiculo atualizar;

    @Inject
    DeletarVeiculo deletar;

    public List<VeiculoDTO> listar(int page, int size) { return busca.listar(page, size); }
    public VeiculoDTO buscarPorId(Long id) { return busca.porId(id); }
    public VeiculoDTO criar(VeiculoDTO dto) { return criar.executar(dto); }
    public VeiculoDTO atualizar(Long id, VeiculoDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
