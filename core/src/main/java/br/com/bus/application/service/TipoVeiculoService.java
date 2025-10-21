package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.TipoVeiculoDTO;
import br.com.bus.application.service.useCase.AtualizarTipoVeiculo;
import br.com.bus.application.service.useCase.BuscaTipoVeiculo;
import br.com.bus.application.service.useCase.CriarTipoVeiculo;
import br.com.bus.application.service.useCase.DeletarTipoVeiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TipoVeiculoService {

    @Inject
    BuscaTipoVeiculo busca;

    @Inject
    CriarTipoVeiculo criar;

    @Inject
    AtualizarTipoVeiculo atualizar;

    @Inject
    DeletarTipoVeiculo deletar;

    public List<TipoVeiculoDTO> listar(int page, int size) { return busca.listar(page, size); }
    public TipoVeiculoDTO buscarPorId(Long id) { return busca.porId(id); }
    public TipoVeiculoDTO criar(TipoVeiculoDTO dto) { return criar.executar(dto); }
    public TipoVeiculoDTO atualizar(Long id, TipoVeiculoDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

