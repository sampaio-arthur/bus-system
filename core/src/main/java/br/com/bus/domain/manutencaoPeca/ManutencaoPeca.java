package br.com.bus.domain.manutencaoPeca;

import java.math.BigDecimal;

import br.com.bus.domain.Manutencao;
import br.com.bus.domain.Peca;
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

    @MapsId("idManutencao")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_manutencao", nullable = false)
    private Manutencao manutencao;

    @MapsId("idPeca")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_peca", nullable = false)
    private Peca peca;

    @Column(name = "quantidade_utilizada")
    private Integer quantidadeUtilizada;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

	public ManutencaoPecaId getId() {
		return id;
	}

	public void setId(ManutencaoPecaId id) {
		this.id = id;
	}

	public Manutencao getManutencao() {
		return manutencao;
	}

	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public Integer getQuantidadeUtilizada() {
		return quantidadeUtilizada;
	}

	public void setQuantidadeUtilizada(Integer quantidadeUtilizada) {
		this.quantidadeUtilizada = quantidadeUtilizada;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
    
}
