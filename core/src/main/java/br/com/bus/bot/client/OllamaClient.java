package br.com.bus.bot.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.bus.bot.dto.OllamaChatRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "ollama")
@Path("/api/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OllamaClient {

    @POST
    String chat(OllamaChatRequest request);
}
