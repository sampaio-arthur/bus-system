package br.com.bus.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa passageiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_passagem", nullable = false)
    private TipoPassagem tipoPassagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pagamento", nullable = false)
    private MetodoPagamento metodoPagamento;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_emissao", nullable = false)
    private LocalDateTime dataEmissao;

    @Column(name = "numero_assento", length = 10)
    private String numeroAssento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Viagem getViagem() { return viagem; }
    public void setViagem(Viagem viagem) { this.viagem = viagem; }

    public Pessoa getPassageiro() { return passageiro; }
    public void setPassageiro(Pessoa passageiro) { this.passageiro = passageiro; }

    public TipoPassagem getTipoPassagem() { return tipoPassagem; }
    public void setTipoPassagem(TipoPassagem tipoPassagem) { this.tipoPassagem = tipoPassagem; }

    public MetodoPagamento getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(MetodoPagamento metodoPagamento) { this.metodoPagamento = metodoPagamento; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDateTime dataEmissao) { this.dataEmissao = dataEmissao; }

    public String getNumeroAssento() { return numeroAssento; }
    public void setNumeroAssento(String numeroAssento) { this.numeroAssento = numeroAssento; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passagem)) return false;
        Passagem passagem = (Passagem) o;
        return id != null && Objects.equals(id, passagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
