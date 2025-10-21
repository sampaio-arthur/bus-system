package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.RotaDTO;
import br.com.bus.application.service.useCase.AtualizarRota;
import br.com.bus.application.service.useCase.BuscaRota;
import br.com.bus.application.service.useCase.CriarRota;
import br.com.bus.application.service.useCase.DeletarRota;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RotaService {

    @Inject
    BuscaRota busca;

    @Inject
    CriarRota criar;

    @Inject
    AtualizarRota atualizar;

    @Inject
    DeletarRota deletar;

    public List<RotaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public RotaDTO buscarPorId(Long id) { return busca.porId(id); }
    public RotaDTO criar(RotaDTO dto) { return criar.executar(dto); }
    public RotaDTO atualizar(Long id, RotaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

