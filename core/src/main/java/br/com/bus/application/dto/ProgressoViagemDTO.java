package br.com.bus.application.dto;

public class ProgressoViagemDTO {

    private Integer idViagem;
    private Integer idPontoParada;
    private String nomeViagem;
    private String nomePontoParada;

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Integer getIdPontoParada() {
        return idPontoParada;
    }

    public void setIdPontoParada(Integer idPontoParada) {
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

