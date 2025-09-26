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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "passagem")
public class Passagem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_compra", nullable = false)
    private LocalDateTime dataCompra;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "desconto_aplicado", precision = 10, scale = 2)
    private BigDecimal descontoAplicado;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Passageiro passageiro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Viagem viagem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public BigDecimal getDescontoAplicado() { return descontoAplicado; }
    public void setDescontoAplicado(BigDecimal descontoAplicado) { this.descontoAplicado = descontoAplicado; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public Passageiro getPassageiro() { return passageiro; }
    public void setPassageiro(Passageiro passageiro) { this.passageiro = passageiro; }

    public Viagem getViagem() { return viagem; }
    public void setViagem(Viagem viagem) { this.viagem = viagem; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passagem)) return false;
        Passagem that = (Passagem) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
