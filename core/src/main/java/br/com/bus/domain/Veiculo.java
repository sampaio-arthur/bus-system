package br.com.bus.domain;

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
@Table(name = "veiculo")
public class Veiculo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chassi", nullable = false, length = 30, unique = true)
    private String chassi;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "capacidade")
    private Integer capacidade;

    @Column(name = "modelo", length = 80)
    private String modelo;

    @Column(name = "placa", length = 10, unique = true)
    private String placa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_veiculo", nullable = false)
    private TipoVeiculo tipo;

    @OneToMany(mappedBy = "veiculo")
    private List<Manutencao> manutencoes = new ArrayList<>();

    @OneToMany(mappedBy = "veiculo")
    private List<Viagem> viagens = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public TipoVeiculo getTipo() { return tipo; }
    public void setTipo(TipoVeiculo tipo) { this.tipo = tipo; }

    public List<Manutencao> getManutencoes() { return manutencoes; }
    public void setManutencoes(List<Manutencao> manutencoes) { this.manutencoes = manutencoes; }

    public List<Viagem> getViagens() { return viagens; }
    public void setViagens(List<Viagem> viagens) { this.viagens = viagens; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return id != null && Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
