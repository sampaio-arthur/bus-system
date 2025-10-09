package br.com.bus.domain;

import java.math.BigDecimal;
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
@Table(name = "peca")
public class Peca extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_unitario", precision = 12, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "fabricante", length = 120)
    private String fabricante;

    @Column(name = "quantidade")
    private Integer quantidade;

    @OneToMany(mappedBy = "peca")
    private List<ManutencaoPeca> manutencaoPecas = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public List<ManutencaoPeca> getManutencaoPecas() { return manutencaoPecas; }
    public void setManutencaoPecas(List<ManutencaoPeca> manutencaoPecas) { this.manutencaoPecas = manutencaoPecas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peca)) return false;
        Peca peca = (Peca) o;
        return id != null && Objects.equals(id, peca.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
