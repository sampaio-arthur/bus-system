package br.com.bus.application.dto;

import java.math.BigDecimal;

public class ManutencaoPecaDTO {

    private Integer idManutencao;
    private Integer idPeca;
    private Integer quantidadeUtilizada;
    private BigDecimal valorUnitario;
    private String nomePeca;
    private String nomeManutencao;

    public Integer getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Integer idManutencao) {
        this.idManutencao = idManutencao;
    }

    public Integer getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Integer idPeca) {
        this.idPeca = idPeca;
    }

    public Integer getQuantidadeUtilizada() {
        return quantidadeUtilizada;
    }

    public void setQuantidadeUtilizada(Integer quantidadeUtilizada) {
        this.quantidadeUtilizada = quantidadeUtilizada;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public String getNomeManutencao() {
        return nomeManutencao;
    }

    public void setNomeManutencao(String nomeManutencao) {
        this.nomeManutencao = nomeManutencao;
    }
}
