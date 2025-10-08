package br.com.bus.bot.dto;

public class OllamaMessage {
    public String role;
    public String content;

    public OllamaMessage() {
    }

    public OllamaMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
