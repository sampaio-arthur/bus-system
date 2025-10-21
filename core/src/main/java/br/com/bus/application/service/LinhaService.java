package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.LinhaDTO;
import br.com.bus.application.service.useCase.AtualizarLinha;
import br.com.bus.application.service.useCase.BuscaLinha;
import br.com.bus.application.service.useCase.CriarLinha;
import br.com.bus.application.service.useCase.DeletarLinha;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LinhaService {

    @Inject
    BuscaLinha busca;

    @Inject
    CriarLinha criar;

    @Inject
    AtualizarLinha atualizar;

    @Inject
    DeletarLinha deletar;

    public List<LinhaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public LinhaDTO buscarPorId(Long id) { return busca.porId(id); }
    public LinhaDTO criar(LinhaDTO dto) { return criar.executar(dto); }
    public LinhaDTO atualizar(Long id, LinhaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

