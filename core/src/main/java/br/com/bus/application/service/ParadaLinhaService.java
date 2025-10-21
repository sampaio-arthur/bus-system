package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.ParadaLinhaDTO;
import br.com.bus.application.service.useCase.AtualizarParadaLinha;
import br.com.bus.application.service.useCase.BuscaParadaLinha;
import br.com.bus.application.service.useCase.CriarParadaLinha;
import br.com.bus.application.service.useCase.DeletarParadaLinha;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ParadaLinhaService {

    @Inject
    BuscaParadaLinha busca;

    @Inject
    CriarParadaLinha criar;

    @Inject
    AtualizarParadaLinha atualizar;

    @Inject
    DeletarParadaLinha deletar;

    public List<ParadaLinhaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public ParadaLinhaDTO buscarPorId(Long id) { return busca.porId(id); }
    public ParadaLinhaDTO criar(ParadaLinhaDTO dto) { return criar.executar(dto); }
    public ParadaLinhaDTO atualizar(Long id, ParadaLinhaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

