package br.com.bus.application.dto;

import java.time.LocalDateTime;

public class ProgressoViagemDTO {

    private LocalDateTime data;
    private Long idViagem;
    private Long idPontoParada;
    private String nomeViagem;
    private String nomePontoParada;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Long idViagem) {
        this.idViagem = idViagem;
    }

    public Long getIdPontoParada() {
        return idPontoParada;
    }

    public void setIdPontoParada(Long idPontoParada) {
        this.idPontoParada = idPontoParada;
    }

    public String getNomeViagem() {
        return nomeViagem;
    }

    public void setNomeViagem(String nomeViagem) {
        this.nomeViagem = nomeViagem;
    }

    public String getNomePontoParada() {
        return nomePontoParada;
    }

    public void setNomePontoParada(String nomePontoParada) {
        this.nomePontoParada = nomePontoParada;
    }
}

