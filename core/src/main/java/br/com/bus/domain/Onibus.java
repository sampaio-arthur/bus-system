package br.com.bus.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "onibus")
@DiscriminatorValue("ONIBUS")
public class Onibus extends Veiculo {

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
