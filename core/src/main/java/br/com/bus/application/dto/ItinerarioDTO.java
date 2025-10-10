package br.com.bus.application.dto;

public class ItinerarioDTO {

    private Integer idLinha;
    private Integer idPontoParada;
    private String nomeLinha;
    private String nomePontoParada;

    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
    }

    public Integer getIdPontoParada() {
        return idPontoParada;
    }

    public void setIdPontoParada(Integer idPontoParada) {
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

