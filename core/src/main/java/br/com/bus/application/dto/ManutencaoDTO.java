package br.com.bus.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class ManutencaoDTO {

	private Long id;
	private VeiculoDTO veiculo;
	private PessoaDTO mecanico;
	private String descricao;
	private BigDecimal custoTotal;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private Set<ManutencaoPecaDTO> manutencaoPecas = new LinkedHashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VeiculoDTO getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(VeiculoDTO veiculo) {
		this.veiculo = veiculo;
	}
	public PessoaDTO getMecanico() {
		return mecanico;
	}
	public void setMecanico(PessoaDTO mecanico) {
		this.mecanico = mecanico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getCustoTotal() {
		return custoTotal;
	}
	public void setCustoTotal(BigDecimal custoTotal) {
		this.custoTotal = custoTotal;
	}
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDateTime getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	public Set<ManutencaoPecaDTO> getManutencaoPecas() {
		return manutencaoPecas;
	}
	public void setManutencaoPecas(Set<ManutencaoPecaDTO> manutencaoPecas) {
		this.manutencaoPecas = manutencaoPecas;
	}
	
}
