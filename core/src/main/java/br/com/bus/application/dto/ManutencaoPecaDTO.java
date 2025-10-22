package br.com.bus.application.dto;

import java.math.BigDecimal;

public class ManutencaoPecaDTO {

    private Long idManutencao;
    private Long idPeca;
    private Integer quantidadeUtilizada;
    private BigDecimal valorUnitario;
    private String nomePeca;
    private String nomeManutencao;

    public Long getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Long idManutencao) {
        this.idManutencao = idManutencao;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
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
