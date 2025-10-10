package br.com.bus.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class TipoVeiculoDTO {

	private Integer id;
	private String descricao;
	private Set<VeiculoDTO> veiculos = new LinkedHashSet<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Set<VeiculoDTO> getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(Set<VeiculoDTO> veiculos) {
		this.veiculos = veiculos;
	}
	
}