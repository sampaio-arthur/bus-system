package br.com.bus.application.dto;

import java.math.BigDecimal;

public class GastoManutencaoPorVeiculoDTO {

    private String placa;
    private BigDecimal valorTotalGasto;

    public GastoManutencaoPorVeiculoDTO() {}

    public GastoManutencaoPorVeiculoDTO(String placa, BigDecimal valorTotalGasto) {
        this.placa = placa;
        this.valorTotalGasto = valorTotalGasto;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public BigDecimal getValorTotalGasto() { return valorTotalGasto; }
    public void setValorTotalGasto(BigDecimal valorTotalGasto) { this.valorTotalGasto = valorTotalGasto; }
}

