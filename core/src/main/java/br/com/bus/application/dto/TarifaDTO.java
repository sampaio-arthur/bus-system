package br.com.bus.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TarifaDTO {

	private Long id;
    private BigDecimal valor;
    private String descricao;
    private Boolean ativo = true;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LinhaDTO linha;
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public LinhaDTO getLinha() { return linha; }
    public void setLinha(LinhaDTO linha) { this.linha = linha; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    
}