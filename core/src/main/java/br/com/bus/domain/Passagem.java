package br.com.bus.domain;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "passagem")
public class Passagem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passagem")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    public Pessoa passageiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    public Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pagamento", nullable = false)
    public MetodoPagamento metodoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_passagem", nullable = false)
    public TipoPassagem tipoPassagem;

    @Column(name = "valor")
    public BigDecimal valor;

    @Column(name = "data_emissao", length = 255)
    public String dataEmissao;

    @Column(name = "numero_assento")
    public Short numeroAssento;
}
