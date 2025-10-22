package br.com.bus.application.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.service.useCase.AtualizarProgressoViagem;
import br.com.bus.application.service.useCase.BuscaProgressoViagem;
import br.com.bus.application.service.useCase.CriarProgressoViagem;
import br.com.bus.application.service.useCase.DeletarProgressoViagem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProgressoViagemService {

    @Inject
    BuscaProgressoViagem busca;

    @Inject
    CriarProgressoViagem criar;

    @Inject
    AtualizarProgressoViagem atualizar;

    @Inject
    DeletarProgressoViagem deletar;

    public List<ProgressoViagemDTO> listar(int page, int size) { return busca.listar(page, size); }
    public ProgressoViagemDTO buscarPorId(LocalDateTime data, Integer idViagem, Integer idPontoParada) {
        return busca.porId(data, idViagem, idPontoParada);
    }
    public ProgressoViagemDTO criar(ProgressoViagemDTO dto) { return criar.executar(dto); }
    public ProgressoViagemDTO atualizar(LocalDateTime data, Integer idViagem, Integer idPontoParada, ProgressoViagemDTO dto) {
        return atualizar.executar(data, idViagem, idPontoParada, dto);
    }
    public void deletar(LocalDateTime data, Integer idViagem, Integer idPontoParada) {
        deletar.executar(data, idViagem, idPontoParada);
    }
}
