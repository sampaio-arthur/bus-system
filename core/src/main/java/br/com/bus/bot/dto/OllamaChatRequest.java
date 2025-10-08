package br.com.bus.bot.dto;

import java.util.List;

public class OllamaChatRequest {
    public String model;
    public List<OllamaMessage> messages;

    public OllamaChatRequest() {
    }

    public OllamaChatRequest(String model, List<OllamaMessage> messages) {
        this.model = model;
        this.messages = messages;
    }
}
