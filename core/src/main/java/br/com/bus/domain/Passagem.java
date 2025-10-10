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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa passageiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pagamento", nullable = false)
    private MetodoPagamento metodoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_passagem", nullable = false)
    private TipoPassagem tipoPassagem;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_emissao", length = 255)
    private String dataEmissao;

    @Column(name = "numero_assento")
    private Short numeroAssento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Pessoa passageiro) {
		this.passageiro = passageiro;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public TipoPassagem getTipoPassagem() {
		return tipoPassagem;
	}

	public void setTipoPassagem(TipoPassagem tipoPassagem) {
		this.tipoPassagem = tipoPassagem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Short getNumeroAssento() {
		return numeroAssento;
	}

	public void setNumeroAssento(Short numeroAssento) {
		this.numeroAssento = numeroAssento;
	}
    
}
