package br.com.bus.application.controller;

import br.com.bus.application.dto.GastoManutencaoPorVeiculoDTO;
import br.com.bus.application.dto.MediaPassageirosPorLinhaDTO;
import br.com.bus.application.dto.PontosTuristicosPorCidadeDTO;
import br.com.bus.application.service.RelatorioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/relatorios")
@Produces(MediaType.APPLICATION_JSON)
public class RelatorioController {

    @Inject
    RelatorioService service;

    @GET
    @Path("/gastos-manutencao-por-veiculo")
    public List<GastoManutencaoPorVeiculoDTO> gastosManutencaoPorVeiculo(
            @QueryParam("meses") @DefaultValue("24") int meses) {
        int m = meses <= 0 ? 12 : meses;
        return service.gastosManutencaoPorVeiculo(m);
    }

    @GET
    @Path("/pontos-turisticos-por-cidade")
    public List<PontosTuristicosPorCidadeDTO> pontosTuristicosPorCidade() {
        return service.pontosTuristicosPorCidade();
    }

    @GET
    @Path("/media-passageiros-por-linha")
    public List<MediaPassageirosPorLinhaDTO> mediaPassageirosPorLinha(
            @QueryParam("meses") @DefaultValue("24") int meses) {
        int m = meses <= 0 ? 6 : meses;
        return service.mediaPassageirosPorLinha(m);
    }
}
