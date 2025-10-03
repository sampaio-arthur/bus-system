package br.com.bus.application.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PontoParadaDTO {

    private Long id;
    private String nome;
    private String endereco;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean temCobertura;
    private Boolean temBanco;
    private Boolean ativo = true;
    private CidadeDTO cidade;
    private List<ParadaLinhaDTO> paradasLinha = new ArrayList<>();
    private List<PontoTuristicoDTO> pontosTuristicosProximos = new ArrayList<>();
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Boolean getTemCobertura() {
        return temCobertura;
    }

    public void setTemCobertura(Boolean temCobertura) {
        this.temCobertura = temCobertura;
    }

    public Boolean getTemBanco() {
        return temBanco;
    }

    public void setTemBanco(Boolean temBanco) {
        this.temBanco = temBanco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }

    public List<ParadaLinhaDTO> getParadasLinha() {
        return paradasLinha;
    }

    public void setParadasLinha(List<ParadaLinhaDTO> paradasLinha) {
        this.paradasLinha = paradasLinha;
    }

    public List<PontoTuristicoDTO> getPontosTuristicosProximos() {
        return pontosTuristicosProximos;
    }

    public void setPontosTuristicosProximos(List<PontoTuristicoDTO> pontosTuristicosProximos) {
        this.pontosTuristicosProximos = pontosTuristicosProximos;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}