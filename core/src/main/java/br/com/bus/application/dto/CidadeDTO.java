package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class CidadeDTO {

    private Long id;
    private String nome;
    private String estado;
    private String cep;
    private Long populacao;
    private Boolean ativo = true;
    private List<PontoParadaDTO> pontosParada = new ArrayList<>();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<PontoParadaDTO> getPontosParada() {
        return pontosParada;
    }

    public void setPontosParada(List<PontoParadaDTO> pontosParada) {
        this.pontosParada = pontosParada;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}