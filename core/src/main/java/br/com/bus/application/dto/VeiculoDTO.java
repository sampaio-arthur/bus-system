package br.com.bus.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class VeiculoDTO {

	private Integer id;
    private TipoVeiculoDTO tipoVeiculo;
    private String chassi;
    private Short anoFabricacao;
    private Short capacidade;
    private String modelo;
    private String placa;
    private Set<ManutencaoDTO> manutencoes = new LinkedHashSet<>();
    private Set<ViagemDTO> viagens = new LinkedHashSet<>();
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoVeiculoDTO getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculoDTO tipoVeiculo) {
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
	public Set<ManutencaoDTO> getManutencoes() {
		return manutencoes;
	}
	public void setManutencoes(Set<ManutencaoDTO> manutencoes) {
		this.manutencoes = manutencoes;
	}
	public Set<ViagemDTO> getViagens() {
		return viagens;
	}
	public void setViagens(Set<ViagemDTO> viagens) {
		this.viagens = viagens;
	}
    
}
