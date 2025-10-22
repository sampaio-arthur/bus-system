package br.com.bus.application.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.service.ProgressoViagemService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
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

@Path("/progresso-viagens")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProgressoViagemController {

    @Inject
    ProgressoViagemService service;

    @GET
    public List<ProgressoViagemDTO> listar(@QueryParam("page") Integer page,
                                           @QueryParam("size") Integer size) {
        int p = page == null || page < 0 ? 0 : page;
        int s = size == null || size <= 0 ? 20 : size;
        return service.listar(p, s);
    }

    @GET
    @Path("/{idViagem}/{idPontoParada}")
    public ProgressoViagemDTO buscarPorId(@PathParam("idViagem") Long idViagem,
                                          @PathParam("idPontoParada") Long idPontoParada,
                                          @QueryParam("data") String data) {
        LocalDateTime instante = parseData(data);
        return service.buscarPorId(instante, idViagem, idPontoParada);
    }

    @POST
    public Response criar(@Valid ProgressoViagemDTO dto) {
        ProgressoViagemDTO created = service.criar(dto);
        URI location = URI.create(String.format("/progresso-viagens/%d/%d?data=%s",
                created.getIdViagem(), created.getIdPontoParada(), created.getData()));
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/{idViagem}/{idPontoParada}")
    public ProgressoViagemDTO atualizar(@PathParam("idViagem") Long idViagem,
                                        @PathParam("idPontoParada") Long idPontoParada,
                                        @QueryParam("data") String data,
                                        @Valid ProgressoViagemDTO dto) {
        LocalDateTime instante = parseData(data);
        return service.atualizar(instante, idViagem, idPontoParada, dto);
    }

    @DELETE
    @Path("/{idViagem}/{idPontoParada}")
    public Response deletar(@PathParam("idViagem") Long idViagem,
                            @PathParam("idPontoParada") Long idPontoParada,
                            @QueryParam("data") String data) {
        LocalDateTime instante = parseData(data);
        service.deletar(instante, idViagem, idPontoParada);
        return Response.noContent().build();
    }

    private LocalDateTime parseData(String data) {
        if (data == null || data.isBlank()) {
            throw new BadRequestException("Parâmetro 'data' é obrigatório no formato ISO-8601.");
        }
        try {
            return LocalDateTime.parse(data);
        } catch (DateTimeParseException ex) {
            throw new BadRequestException("Parâmetro 'data' inválido. Utilize o formato ISO-8601.", ex);
        }
    }
}
