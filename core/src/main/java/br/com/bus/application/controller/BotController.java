package br.com.bus.application.controller;

import java.util.List;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.service.BotService;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/spaik")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BotController {

    @Inject
    BotService botService;

    @GET
    @Path("/itinerarios")
    public List<ItinerarioDTO> itinerariosPorCidade(@QueryParam("cidade") String cidade) {
        validarCidade(cidade);
        return botService.itinerariosPorCidade(cidade);
    }

    @GET
    @Path("/viagens")
    public List<ViagemDTO> viagensPorCidade(@QueryParam("cidade") String cidade) {
        validarCidade(cidade);
        return botService.viagensPorCidade(cidade);
    }

    @GET
    @Path("/viagens/ultimo-progresso")
    public ProgressoViagemDTO ultimoProgresso(@QueryParam("cidade") String cidade,
                                              @QueryParam("linhaNome") String linhaNome) {
        validarCidade(cidade);
        if (linhaNome == null || linhaNome.isBlank()) {
            throw new BadRequestException("Parâmetro 'linhaNome' é obrigatório");
        }
        return botService.ultimoProgressoPorLinhaECidade(linhaNome, cidade);
    }

    @GET
    @Path("/pontos-parada")
    public List<PontoParadaDTO> pontosParadaPorTuristico(@QueryParam("cidade") String cidade,
                                                         @QueryParam("pontoTuristicoNome") String pontoTuristicoNome) {
        validarCidade(cidade);
        if (pontoTuristicoNome == null || pontoTuristicoNome.isBlank()) {
            throw new BadRequestException("Parâmetro 'pontoTuristicoNome' é obrigatório");
        }
        return botService.pontosParadaPorPontoTuristicoNomeECidade(pontoTuristicoNome, cidade);
    }

    @GET
    @Path("/pontos-turisticos")
    public List<PontoTuristicoDTO> pontosTuristicosPorParada(@QueryParam("cidade") String cidade,
                                                             @QueryParam("pontoParadaNome") String pontoParadaNome) {
        validarCidade(cidade);
        if (pontoParadaNome == null || pontoParadaNome.isBlank()) {
            throw new BadRequestException("Parâmetro 'pontoParadaNome' é obrigatório");
        }
        return botService.pontosTuristicosPorPontoParadaNomeECidade(pontoParadaNome, cidade);
    }

    private void validarCidade(String cidade) {
        if (cidade == null || cidade.isBlank()) {
            throw new BadRequestException("Parâmetro 'cidade' é obrigatório");
        }
    }
}
