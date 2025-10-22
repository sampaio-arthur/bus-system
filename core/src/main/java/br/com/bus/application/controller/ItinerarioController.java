package br.com.bus.application.controller;

import java.net.URI;
import java.util.List;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.service.ItinerarioService;
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

@Path("/itinerarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItinerarioController {

    @Inject
    ItinerarioService service;

    @GET
    public List<ItinerarioDTO> listar(@QueryParam("page") Integer page,
                                      @QueryParam("size") Integer size) {
        int p = page == null || page < 0 ? 0 : page;
        int s = size == null || size <= 0 ? 20 : size;
        return service.listar(p, s);
    }

    @GET
    @Path("/{idLinha}/{idPontoParada}")
    public ItinerarioDTO buscarPorId(@PathParam("idLinha") Integer idLinha,
                                     @PathParam("idPontoParada") Integer idPontoParada) {
        return service.buscarPorId(idLinha, idPontoParada);
    }

    @POST
    public Response criar(@Valid ItinerarioDTO dto) {
        ItinerarioDTO created = service.criar(dto);
        URI location = URI.create(String.format("/itinerarios/%d/%d",
                created.getIdLinha(), created.getIdPontoParada()));
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/{idLinha}/{idPontoParada}")
    public ItinerarioDTO atualizar(@PathParam("idLinha") Integer idLinha,
                                   @PathParam("idPontoParada") Integer idPontoParada,
                                   @Valid ItinerarioDTO dto) {
        return service.atualizar(idLinha, idPontoParada, dto);
    }

    @DELETE
    @Path("/{idLinha}/{idPontoParada}")
    public Response deletar(@PathParam("idLinha") Integer idLinha,
                            @PathParam("idPontoParada") Integer idPontoParada) {
        service.deletar(idLinha, idPontoParada);
        return Response.noContent().build();
    }
}
