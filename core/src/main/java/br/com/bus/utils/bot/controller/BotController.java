package br.com.bus.utils.bot.controller;

import br.com.bus.utils.bot.service.BotService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BotController {

    @Inject
    BotService botService;

    @GET
    public Response conversar(@QueryParam("q") String pergunta) {
        if (pergunta == null || pergunta.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("A pergunta não pode ser vazia.")
                    .build();
        }

        String resposta = botService.processarPergunta(pergunta);
        return Response.ok(resposta).build();
    }
}
