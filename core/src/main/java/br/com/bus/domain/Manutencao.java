package br.com.bus.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

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
    @Column(name = "id_manutencao")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", nullable = false)
    public Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    public Pessoa mecanico;

    @Column(name = "descricao", length = 255)
    public String descricao;

    @Column(name = "custo_total")
    public BigDecimal custoTotal;

    @Column(name = "data_inicio")
    public LocalDateTime dataInicio;

    @Column(name = "data_fim")
    public LocalDateTime dataFim;

    @OneToMany(mappedBy = "manutencao", fetch = FetchType.LAZY)
    public Set<ManutencaoPeca> manutencaoPecas = new LinkedHashSet<>();
}
