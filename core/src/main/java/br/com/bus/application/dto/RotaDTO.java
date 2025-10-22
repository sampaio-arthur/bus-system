package br.com.bus.application.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RotaDTO {

    private Long id;
    private String nome;
    private BigDecimal distanciaKm;
    private Integer tempoEstimado;
    private Boolean ativo = true;
    private LinhaDTO linha;
    private List<ViagemDTO> viagens = new ArrayList<>();

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

    public BigDecimal getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(BigDecimal distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Integer tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LinhaDTO getLinha() {
        return linha;
    }

    public void setLinha(LinhaDTO linha) {
        this.linha = linha;
    }

    public List<ViagemDTO> getViagens() {
        return viagens;
    }

    public void setViagens(List<ViagemDTO> viagens) {
        this.viagens = viagens;
    }

}