package br.com.bus.application.dto;

import java.math.BigDecimal;

public class PassagemDTO {

	private Integer id;
	private PessoaDTO passageiro;
	private ViagemDTO viagem;
	private MetodoPagamentoDTO metodoPagamento;
	private TipoPassagemDTO tipoPassagem;
	private BigDecimal valor;
	private String dataEmissao;
	private Short numeroAssento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PessoaDTO getPassageiro() {
		return passageiro;
	}
	public void setPassageiro(PessoaDTO passageiro) {
		this.passageiro = passageiro;
	}
	public ViagemDTO getViagem() {
		return viagem;
	}
	public void setViagem(ViagemDTO viagem) {
		this.viagem = viagem;
	}
	public MetodoPagamentoDTO getMetodoPagamento() {
		return metodoPagamento;
	}
	public void setMetodoPagamento(MetodoPagamentoDTO metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	public TipoPassagemDTO getTipoPassagem() {
		return tipoPassagem;
	}
	public void setTipoPassagem(TipoPassagemDTO tipoPassagem) {
		this.tipoPassagem = tipoPassagem;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Short getNumeroAssento() {
		return numeroAssento;
	}
	public void setNumeroAssento(Short numeroAssento) {
		this.numeroAssento = numeroAssento;
	}
	
}
