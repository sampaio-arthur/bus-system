package br.com.bus.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "metodo_pagamento")
public class MetodoPagamento extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 120)
    private String descricao;

    @OneToMany(mappedBy = "metodoPagamento")
    private List<Passagem> passagens = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<Passagem> getPassagens() { return passagens; }
    public void setPassagens(List<Passagem> passagens) { this.passagens = passagens; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetodoPagamento)) return false;
        MetodoPagamento that = (MetodoPagamento) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
