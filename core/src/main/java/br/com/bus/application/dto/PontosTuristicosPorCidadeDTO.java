package br.com.bus.application.dto;

public class PontosTuristicosPorCidadeDTO {

    private String cidade;
    private String uf;
    private Long quantidadePontosTuristicos;

    public PontosTuristicosPorCidadeDTO() {}

    public PontosTuristicosPorCidadeDTO(String cidade, String uf, Long quantidadePontosTuristicos) {
        this.cidade = cidade;
        this.uf = uf;
        this.quantidadePontosTuristicos = quantidadePontosTuristicos;
    }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    public Long getQuantidadePontosTuristicos() { return quantidadePontosTuristicos; }
    public void setQuantidadePontosTuristicos(Long quantidadePontosTuristicos) { this.quantidadePontosTuristicos = quantidadePontosTuristicos; }
}

