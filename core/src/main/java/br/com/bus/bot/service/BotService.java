package br.com.bus.bot.service;

import br.com.bus.bot.useCase.ExecutarConsultaNaturalUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BotService {

    @Inject
    ExecutarConsultaNaturalUseCase executarConsultaNaturalUseCase;

    public String processarPergunta(String pergunta) {
        return executarConsultaNaturalUseCase.executar(pergunta);
    }
}
