package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.service.useCase.bot.BuscarItinerariosPorCidade;
import br.com.bus.application.service.useCase.bot.BuscarPontosParadaPorPontoTuristicoNomeECidade;
import br.com.bus.application.service.useCase.bot.BuscarPontosTuristicosPorPontoParadaNomeECidade;
import br.com.bus.application.service.useCase.bot.BuscarUltimoProgressoPorLinhaECidade;
import br.com.bus.application.service.useCase.bot.BuscarViagensPorCidade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BotService {

    @Inject
    BuscarItinerariosPorCidade buscarItinerariosPorCidade;

    @Inject
    BuscarPontosParadaPorPontoTuristicoNomeECidade buscarPontosParadaPorPontoTuristicoNomeECidade;

    @Inject
    BuscarPontosTuristicosPorPontoParadaNomeECidade buscarPontosTuristicosPorPontoParadaNomeECidade;

    @Inject
    BuscarViagensPorCidade buscarViagensPorCidade;

    @Inject
    BuscarUltimoProgressoPorLinhaECidade buscarUltimoProgressoPorLinhaECidade;

    public List<ItinerarioDTO> itinerariosPorCidade(String cidade) {
        return buscarItinerariosPorCidade.executar(cidade);
    }

    public List<PontoParadaDTO> pontosParadaPorPontoTuristicoNomeECidade(String nomePontoTuristico, String cidade) {
        return buscarPontosParadaPorPontoTuristicoNomeECidade.executar(nomePontoTuristico, cidade);
    }

    public List<PontoTuristicoDTO> pontosTuristicosPorPontoParadaNomeECidade(String nomePontoParada, String cidade) {
        return buscarPontosTuristicosPorPontoParadaNomeECidade.executar(nomePontoParada, cidade);
    }

    public List<ViagemDTO> viagensPorCidade(String cidade) {
        return buscarViagensPorCidade.executar(cidade);
    }

    public ProgressoViagemDTO ultimoProgressoPorLinhaECidade(String nomeLinha, String cidade) {
        return buscarUltimoProgressoPorLinhaECidade.executar(nomeLinha, cidade);
    }
}
