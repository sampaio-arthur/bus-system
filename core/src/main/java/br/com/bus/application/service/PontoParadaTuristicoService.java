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
    public PontoParadaTuristicoDTO buscarPorId(Long idPontoParada, Long idPontoTuristico) {
        return busca.porId(idPontoParada, idPontoTuristico);
    }
    public PontoParadaTuristicoDTO criar(PontoParadaTuristicoDTO dto) { return criar.executar(dto); }
    public PontoParadaTuristicoDTO atualizar(Long idPontoParada, Long idPontoTuristico, PontoParadaTuristicoDTO dto) {
        return atualizar.executar(idPontoParada, idPontoTuristico, dto);
    }
    public void deletar(Long idPontoParada, Long idPontoTuristico) { deletar.executar(idPontoParada, idPontoTuristico); }
}
