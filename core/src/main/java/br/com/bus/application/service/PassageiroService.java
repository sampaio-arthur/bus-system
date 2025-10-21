package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.PassageiroDTO;
import br.com.bus.application.service.useCase.AtualizarPassageiro;
import br.com.bus.application.service.useCase.BuscaPassageiro;
import br.com.bus.application.service.useCase.CriarPassageiro;
import br.com.bus.application.service.useCase.DeletarPassageiro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PassageiroService {

    @Inject
    BuscaPassageiro busca;

    @Inject
    CriarPassageiro criar;

    @Inject
    AtualizarPassageiro atualizar;

    @Inject
    DeletarPassageiro deletar;

    public List<PassageiroDTO> listar(int page, int size) { return busca.listar(page, size); }
    public PassageiroDTO buscarPorId(Long id) { return busca.porId(id); }
    public PassageiroDTO criar(PassageiroDTO dto) { return criar.executar(dto); }
    public PassageiroDTO atualizar(Long id, PassageiroDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

