package br.com.bus.domain;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

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
@Table(name = "tipo_passagem")
public class TipoPassagem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_passagem")
    private Long id;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "porcentagem_desconto", precision = 5, scale = 2)
    private BigDecimal porcentagemDesconto;

    @OneToMany(mappedBy = "tipoPassagem", fetch = FetchType.LAZY)
    private Set<Passagem> passagens = new LinkedHashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(Set<Passagem> passagens) {
        this.passagens = passagens;
    }
    
    public BigDecimal getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(BigDecimal porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }
    
}
