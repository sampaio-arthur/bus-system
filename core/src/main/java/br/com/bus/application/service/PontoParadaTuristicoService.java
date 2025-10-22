package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.PontoParadaTuristicoDTO;
import br.com.bus.application.service.useCase.AtualizarPontoParadaTuristico;
import br.com.bus.application.service.useCase.BuscaPontoParadaTuristico;
import br.com.bus.application.service.useCase.CriarPontoParadaTuristico;
import br.com.bus.application.service.useCase.DeletarPontoParadaTuristico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PontoParadaTuristicoService {

    @Inject
    BuscaPontoParadaTuristico busca;

    @Inject
    CriarPontoParadaTuristico criar;

    @Inject
    AtualizarPontoParadaTuristico atualizar;

    @Inject
    DeletarPontoParadaTuristico deletar;

    public List<PontoParadaTuristicoDTO> listar(int page, int size) { return busca.listar(page, size); }
    public PontoParadaTuristicoDTO buscarPorId(Long id) { return busca.porId(id); }
    public PontoParadaTuristicoDTO criar(PontoParadaTuristicoDTO dto) { return criar.executar(dto); }
    public PontoParadaTuristicoDTO atualizar(Long id, PontoParadaTuristicoDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
