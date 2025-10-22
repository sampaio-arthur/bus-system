package br.com.bus.utils.bot.useCase;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.bus.utils.bot.client.OllamaClient;
import br.com.bus.utils.bot.dto.OllamaChatRequest;
import br.com.bus.utils.bot.dto.OllamaMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExecutarConsultaNaturalUseCase {

    @Inject
    @RestClient
    OllamaClient ollamaClient;

    public String executar(String pergunta) {
        var request = new OllamaChatRequest(
                "phi3:mini", // depois podemos trocar por llama3:8b
                List.of(new OllamaMessage("user", pergunta)));

        return ollamaClient.chat(request);
    }
}
