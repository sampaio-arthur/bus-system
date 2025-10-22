package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.ManutencaoDTO;
import br.com.bus.application.service.useCase.AtualizarManutencao;
import br.com.bus.application.service.useCase.BuscaManutencao;
import br.com.bus.application.service.useCase.CriarManutencao;
import br.com.bus.application.service.useCase.DeletarManutencao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ManutencaoService {

    @Inject
    BuscaManutencao busca;

    @Inject
    CriarManutencao criar;

    @Inject
    AtualizarManutencao atualizar;

    @Inject
    DeletarManutencao deletar;

    public List<ManutencaoDTO> listar(int page, int size) { return busca.listar(page, size); }
    public ManutencaoDTO buscarPorId(Long id) { return busca.porId(id); }
    public ManutencaoDTO criar(ManutencaoDTO dto) { return criar.executar(dto); }
    public ManutencaoDTO atualizar(Long id, ManutencaoDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
