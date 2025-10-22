package br.com.bus.application.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PontoTuristicoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private String endereco;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean ativo = true;
    private List<PontoParadaDTO> pontosParadaProximos = new ArrayList<>();
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public List<PontoParadaDTO> getPontosParadaProximos() { return pontosParadaProximos; }
    public void setPontosParadaProximos(List<PontoParadaDTO> pontosParadaProximos) { this.pontosParadaProximos = pontosParadaProximos; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}
