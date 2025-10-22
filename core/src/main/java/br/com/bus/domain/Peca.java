package br.com.bus.domain;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.bus.domain.manutencaoPeca.ManutencaoPeca;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "peca")
public class Peca extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peca")
    private Long id;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "fabricante", length = 255)
    private String fabricante;

    @Column(name = "quantidade")
    private Integer quantidade;

    @OneToMany(mappedBy = "peca", fetch = FetchType.LAZY)
    private Set<ManutencaoPeca> manutencoes = new LinkedHashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Set<ManutencaoPeca> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(Set<ManutencaoPeca> manutencoes) {
		this.manutencoes = manutencoes;
	}
    
}
