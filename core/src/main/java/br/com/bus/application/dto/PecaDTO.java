package br.com.bus.application.dto;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class PecaDTO {

	private Long id;
	private BigDecimal valorUnitario;
	private String nome;
	private String fabricante;
	private Integer quantidade;
	private Set<ManutencaoPecaDTO> manutencoes = new LinkedHashSet<>();
	
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
	public Set<ManutencaoPecaDTO> getManutencoes() {
		return manutencoes;
	}
	public void setManutencoes(Set<ManutencaoPecaDTO> manutencoes) {
		this.manutencoes = manutencoes;
	}
	
}
