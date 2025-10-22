package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.ManutencaoPecaDTO;
import br.com.bus.application.service.useCase.AtualizarManutencaoPeca;
import br.com.bus.application.service.useCase.BuscaManutencaoPeca;
import br.com.bus.application.service.useCase.CriarManutencaoPeca;
import br.com.bus.application.service.useCase.DeletarManutencaoPeca;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ManutencaoPecaService {

    @Inject
    BuscaManutencaoPeca busca;

    @Inject
    CriarManutencaoPeca criar;

    @Inject
    AtualizarManutencaoPeca atualizar;

    @Inject
    DeletarManutencaoPeca deletar;

    public List<ManutencaoPecaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public ManutencaoPecaDTO buscarPorId(Long idManutencao, Long idPeca) { return busca.porId(idManutencao, idPeca); }
    public ManutencaoPecaDTO criar(ManutencaoPecaDTO dto) { return criar.executar(dto); }
    public ManutencaoPecaDTO atualizar(Long idManutencao, Long idPeca, ManutencaoPecaDTO dto) {
        return atualizar.executar(idManutencao, idPeca, dto);
    }
    public void deletar(Long idManutencao, Long idPeca) { deletar.executar(idManutencao, idPeca); }
}
