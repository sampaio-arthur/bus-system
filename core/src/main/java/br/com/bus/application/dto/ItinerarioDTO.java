package br.com.bus.application.dto;

public class ItinerarioDTO {

    private Long idLinha;
    private Long idPontoParada;
    private String nomeLinha;
    private String nomePontoParada;

    public Long getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    public Long getIdPontoParada() {
        return idPontoParada;
    }

    public void setIdPontoParada(Long idPontoParada) {
        this.idPontoParada = idPontoParada;
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }

    public String getNomePontoParada() {
        return nomePontoParada;
    }

    public void setNomePontoParada(String nomePontoParada) {
        this.nomePontoParada = nomePontoParada;
    }
}

