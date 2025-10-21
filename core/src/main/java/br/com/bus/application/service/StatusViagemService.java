package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.StatusViagemDTO;
import br.com.bus.application.service.useCase.AtualizarStatusViagem;
import br.com.bus.application.service.useCase.BuscaStatusViagem;
import br.com.bus.application.service.useCase.CriarStatusViagem;
import br.com.bus.application.service.useCase.DeletarStatusViagem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StatusViagemService {

    @Inject
    BuscaStatusViagem busca;

    @Inject
    CriarStatusViagem criar;

    @Inject
    AtualizarStatusViagem atualizar;

    @Inject
    DeletarStatusViagem deletar;

    public List<StatusViagemDTO> listar(int page, int size) { return busca.listar(page, size); }
    public StatusViagemDTO buscarPorId(Long id) { return busca.porId(id); }
    public StatusViagemDTO criar(StatusViagemDTO dto) { return criar.executar(dto); }
    public StatusViagemDTO atualizar(Long id, StatusViagemDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

