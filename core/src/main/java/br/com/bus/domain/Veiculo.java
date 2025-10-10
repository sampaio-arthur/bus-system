package br.com.bus.domain;

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
@Table(name = "veiculo")
public class Veiculo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_veiculo", nullable = false)
    public TipoVeiculo tipoVeiculo;

    @Column(name = "chassi", length = 17, nullable = false)
    public String chassi;

    @Column(name = "ano_fabricacao")
    public Short anoFabricacao;

    @Column(name = "capacidade")
    public Short capacidade;

    @Column(name = "modelo", length = 255)
    public String modelo;

    @Column(name = "placa", length = 7)
    public String placa;

    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY)
    public Set<Manutencao> manutencoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY)
    public Set<Viagem> viagens = new LinkedHashSet<>();
}
