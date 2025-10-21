package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.service.useCase.AtualizarPontoParada;
import br.com.bus.application.service.useCase.BuscaPontoParada;
import br.com.bus.application.service.useCase.CriarPontoParada;
import br.com.bus.application.service.useCase.DeletarPontoParada;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PontoParadaService {

    @Inject
    BuscaPontoParada busca;

    @Inject
    CriarPontoParada criar;

    @Inject
    AtualizarPontoParada atualizar;

    @Inject
    DeletarPontoParada deletar;

    public List<PontoParadaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public PontoParadaDTO buscarPorId(Long id) { return busca.porId(id); }
    public PontoParadaDTO criar(PontoParadaDTO dto) { return criar.executar(dto); }
    public PontoParadaDTO atualizar(Long id, PontoParadaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

