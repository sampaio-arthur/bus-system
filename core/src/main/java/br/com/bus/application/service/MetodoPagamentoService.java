package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.MetodoPagamentoDTO;
import br.com.bus.application.service.useCase.AtualizarMetodoPagamento;
import br.com.bus.application.service.useCase.BuscaMetodoPagamento;
import br.com.bus.application.service.useCase.CriarMetodoPagamento;
import br.com.bus.application.service.useCase.DeletarMetodoPagamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MetodoPagamentoService {

    @Inject
    BuscaMetodoPagamento busca;

    @Inject
    CriarMetodoPagamento criar;

    @Inject
    AtualizarMetodoPagamento atualizar;

    @Inject
    DeletarMetodoPagamento deletar;

    public List<MetodoPagamentoDTO> listar(int page, int size) { return busca.listar(page, size); }
    public MetodoPagamentoDTO buscarPorId(Long id) { return busca.porId(id); }
    public MetodoPagamentoDTO criar(MetodoPagamentoDTO dto) { return criar.executar(dto); }
    public MetodoPagamentoDTO atualizar(Long id, MetodoPagamentoDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
