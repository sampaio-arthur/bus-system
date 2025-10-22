package br.com.bus.application.controller;

import java.net.URI;
import java.util.List;

import br.com.bus.application.dto.ManutencaoPecaDTO;
import br.com.bus.application.service.ManutencaoPecaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/manutencoes-pecas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ManutencaoPecaController {

    @Inject
    ManutencaoPecaService service;

    @GET
    public List<ManutencaoPecaDTO> listar(@QueryParam("page") Integer page,
                                          @QueryParam("size") Integer size) {
        int p = page == null || page < 0 ? 0 : page;
        int s = size == null || size <= 0 ? 20 : size;
        return service.listar(p, s);
    }

    @GET
    @Path("/{idManutencao}/{idPeca}")
    public ManutencaoPecaDTO buscarPorId(@PathParam("idManutencao") Long idManutencao,
                                         @PathParam("idPeca") Long idPeca) {
        return service.buscarPorId(idManutencao, idPeca);
    }

    @POST
    public Response criar(@Valid ManutencaoPecaDTO dto) {
        ManutencaoPecaDTO created = service.criar(dto);
        URI location = URI.create(String.format("/manutencoes-pecas/%d/%d",
                created.getIdManutencao(), created.getIdPeca()));
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/{idManutencao}/{idPeca}")
    public ManutencaoPecaDTO atualizar(@PathParam("idManutencao") Long idManutencao,
                                       @PathParam("idPeca") Long idPeca,
                                       @Valid ManutencaoPecaDTO dto) {
        return service.atualizar(idManutencao, idPeca, dto);
    }

    @DELETE
    @Path("/{idManutencao}/{idPeca}")
    public Response deletar(@PathParam("idManutencao") Long idManutencao,
                            @PathParam("idPeca") Long idPeca) {
        service.deletar(idManutencao, idPeca);
        return Response.noContent().build();
    }
}
