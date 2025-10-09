package br.com.bus.domain;

import java.math.BigDecimal;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "manutencao_peca")
public class ManutencaoPeca extends PanacheEntityBase {

    @EmbeddedId
    private ManutencaoPecaId id = new ManutencaoPecaId();

    @MapsId("manutencaoId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_manutencao", nullable = false)
    private Manutencao manutencao;

    @MapsId("pecaId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_peca", nullable = false)
    private Peca peca;

    @Column(name = "quantidade_utilizada")
    private Integer quantidadeUtilizada;

    @Column(name = "valor_unitario", precision = 12, scale = 2)
    private BigDecimal valorUnitario;

    public ManutencaoPecaId getId() { return id; }
    public void setId(ManutencaoPecaId id) { this.id = id; }

    public Manutencao getManutencao() { return manutencao; }
    public void setManutencao(Manutencao manutencao) { this.manutencao = manutencao; }

    public Peca getPeca() { return peca; }
    public void setPeca(Peca peca) { this.peca = peca; }

    public Integer getQuantidadeUtilizada() { return quantidadeUtilizada; }
    public void setQuantidadeUtilizada(Integer quantidadeUtilizada) { this.quantidadeUtilizada = quantidadeUtilizada; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManutencaoPeca)) return false;
        ManutencaoPeca that = (ManutencaoPeca) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
