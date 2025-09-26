package br.com.bus.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "carro")
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {

    private String categoria;

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

}