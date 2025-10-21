package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.CidadeDTO;
import br.com.bus.application.service.useCase.AtualizarCidade;
import br.com.bus.application.service.useCase.BuscaCidade;
import br.com.bus.application.service.useCase.CriarCidade;
import br.com.bus.application.service.useCase.DeletarCidade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CidadeService {

    @Inject
    BuscaCidade busca;

    @Inject
    CriarCidade criar;

    @Inject
    AtualizarCidade atualizar;

    @Inject
    DeletarCidade deletar;

    public List<CidadeDTO> listar(int page, int size) { return busca.listar(page, size); }
    public CidadeDTO buscarPorId(Long id) { return busca.porId(id); }
    public CidadeDTO criar(CidadeDTO dto) { return criar.executar(dto); }
    public CidadeDTO atualizar(Long id, CidadeDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

