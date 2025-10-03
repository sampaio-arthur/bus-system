package br.com.bus.application.dto;

public class OnibusDTO extends VeiculoDTO {

    private String numeroLinha;

    private Boolean temArCondicionado;

    private Boolean acessivelCadeirante;

    public String getNumeroLinha() { return numeroLinha; }
    public void setNumeroLinha(String numeroLinha) { this.numeroLinha = numeroLinha; }

    public Boolean getTemArCondicionado() { return temArCondicionado; }
    public void setTemArCondicionado(Boolean temArCondicionado) { this.temArCondicionado = temArCondicionado; }

    public Boolean getAcessivelCadeirante() { return acessivelCadeirante; }
    public void setAcessivelCadeirante(Boolean acessivelCadeirante) { this.acessivelCadeirante = acessivelCadeirante; }

}
