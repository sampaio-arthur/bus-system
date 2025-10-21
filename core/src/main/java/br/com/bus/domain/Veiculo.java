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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_veiculo", nullable = false)
    private TipoVeiculo tipoVeiculo;

    @Column(name = "chassi", length = 17, nullable = false)
    private String chassi;

    @Column(name = "ano_fabricacao")
    private Short anoFabricacao;

    @Column(name = "capacidade")
    private Short capacidade;

    @Column(name = "modelo", length = 255)
    private String modelo;

    @Column(name = "placa", length = 7)
    private String placa;

    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY)
    private Set<Manutencao> manutencoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY)
    private Set<Viagem> viagens = new LinkedHashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Short getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Short anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Short getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Short capacidade) {
		this.capacidade = capacidade;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Set<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(Set<Manutencao> manutencoes) {
		this.manutencoes = manutencoes;
	}

	public Set<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(Set<Viagem> viagens) {
		this.viagens = viagens;
	}
    
}
