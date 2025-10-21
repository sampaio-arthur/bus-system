package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.FeedbackPassageiroDTO;
import br.com.bus.application.service.useCase.AtualizarFeedbackPassageiro;
import br.com.bus.application.service.useCase.BuscaFeedbackPassageiro;
import br.com.bus.application.service.useCase.CriarFeedbackPassageiro;
import br.com.bus.application.service.useCase.DeletarFeedbackPassageiro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FeedbackPassageiroService {

    @Inject
    BuscaFeedbackPassageiro busca;

    @Inject
    CriarFeedbackPassageiro criar;

    @Inject
    AtualizarFeedbackPassageiro atualizar;

    @Inject
    DeletarFeedbackPassageiro deletar;

    public List<FeedbackPassageiroDTO> listar(int page, int size) { return busca.listar(page, size); }
    public FeedbackPassageiroDTO buscarPorId(Long id) { return busca.porId(id); }
    public FeedbackPassageiroDTO criar(FeedbackPassageiroDTO dto) { return criar.executar(dto); }
    public FeedbackPassageiroDTO atualizar(Long id, FeedbackPassageiroDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

