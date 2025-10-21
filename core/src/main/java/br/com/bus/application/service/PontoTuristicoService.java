package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.service.useCase.AtualizarPontoTuristico;
import br.com.bus.application.service.useCase.BuscaPontoTuristico;
import br.com.bus.application.service.useCase.CriarPontoTuristico;
import br.com.bus.application.service.useCase.DeletarPontoTuristico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PontoTuristicoService {

    @Inject
    BuscaPontoTuristico busca;

    @Inject
    CriarPontoTuristico criar;

    @Inject
    AtualizarPontoTuristico atualizar;

    @Inject
    DeletarPontoTuristico deletar;

    public List<PontoTuristicoDTO> listar(int page, int size) { return busca.listar(page, size); }
    public PontoTuristicoDTO buscarPorId(Long id) { return busca.porId(id); }
    public PontoTuristicoDTO criar(PontoTuristicoDTO dto) { return criar.executar(dto); }
    public PontoTuristicoDTO atualizar(Long id, PontoTuristicoDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

