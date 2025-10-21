package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.TarifaDTO;
import br.com.bus.application.service.useCase.AtualizarTarifa;
import br.com.bus.application.service.useCase.BuscaTarifa;
import br.com.bus.application.service.useCase.CriarTarifa;
import br.com.bus.application.service.useCase.DeletarTarifa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TarifaService {

    @Inject
    BuscaTarifa busca;

    @Inject
    CriarTarifa criar;

    @Inject
    AtualizarTarifa atualizar;

    @Inject
    DeletarTarifa deletar;

    public List<TarifaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public TarifaDTO buscarPorId(Long id) { return busca.porId(id); }
    public TarifaDTO criar(TarifaDTO dto) { return criar.executar(dto); }
    public TarifaDTO atualizar(Long id, TarifaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

