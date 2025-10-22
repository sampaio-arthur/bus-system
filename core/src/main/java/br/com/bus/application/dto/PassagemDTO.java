package br.com.bus.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PassagemDTO {

    private Long id;
    private LocalDateTime dataCompra;
    private BigDecimal valor;
    private BigDecimal descontoAplicado;
    private Boolean ativo = true;
    private PassageiroDTO passageiro;
    private ViagemDTO viagem;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public BigDecimal getDescontoAplicado() { return descontoAplicado; }
    public void setDescontoAplicado(BigDecimal descontoAplicado) { this.descontoAplicado = descontoAplicado; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public PassageiroDTO getPassageiro() { return passageiro; }
    public void setPassageiro(PassageiroDTO passageiro) { this.passageiro = passageiro; }

    public ViagemDTO getViagem() { return viagem; }
    public void setViagem(ViagemDTO viagem) { this.viagem = viagem; }
    
}
