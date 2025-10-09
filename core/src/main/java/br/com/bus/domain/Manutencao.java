package br.com.bus.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "manutencao")
public class Manutencao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa responsavel;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "custo_total", precision = 12, scale = 2)
    private BigDecimal custoTotal;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @OneToMany(mappedBy = "manutencao")
    private List<ManutencaoPeca> pecas = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public Pessoa getResponsavel() { return responsavel; }
    public void setResponsavel(Pessoa responsavel) { this.responsavel = responsavel; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getCustoTotal() { return custoTotal; }
    public void setCustoTotal(BigDecimal custoTotal) { this.custoTotal = custoTotal; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public List<ManutencaoPeca> getPecas() { return pecas; }
    public void setPecas(List<ManutencaoPeca> pecas) { this.pecas = pecas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manutencao)) return false;
        Manutencao that = (Manutencao) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
