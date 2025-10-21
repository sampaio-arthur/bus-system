package br.com.bus.application.controller;

import java.net.URI;
import java.util.List;

import br.com.bus.application.dto.EmpresaTransporteDTO;
import br.com.bus.application.service.EmpresaTransporteService;
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

@Path("/empresas-transporte")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpresaTransporteController {

    @Inject
    EmpresaTransporteService service;

    @GET
    public List<EmpresaTransporteDTO> listar(@QueryParam("page") Integer page,
                                             @QueryParam("size") Integer size) {
        int p = page == null || page < 0 ? 0 : page;
        int s = size == null || size <= 0 ? 20 : size;
        return service.listar(p, s);
    }

    @GET
    @Path("/{id}")
    public EmpresaTransporteDTO buscarPorId(@PathParam("id") Long id) { return service.buscarPorId(id); }

    @POST
    public Response criar(@Valid EmpresaTransporteDTO dto) {
        EmpresaTransporteDTO created = service.criar(dto);
        return Response.created(URI.create("/empresas-transporte/" + created.getId())).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public EmpresaTransporteDTO atualizar(@PathParam("id") Long id, @Valid EmpresaTransporteDTO dto) { return service.atualizar(id, dto); }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}

