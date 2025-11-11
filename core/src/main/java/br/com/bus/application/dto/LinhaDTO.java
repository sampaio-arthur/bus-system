package br.com.bus.application.dto;

import java.math.BigDecimal;

public class LinhaDTO {

    private Long id;
    private String nome;
    private String codigo;
    private BigDecimal tarifa;
    private Boolean ativo = true;
    private Integer tempoPercursoEstimado;
    
    public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getTempoPercursoEstimado() {
        return tempoPercursoEstimado;
    }

    public void setTempoPercursoEstimado(Integer tempoPercursoEstimado) {
        this.tempoPercursoEstimado = tempoPercursoEstimado;
    }

}
