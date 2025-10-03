package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class TipoVeiculoDTO {

    private Long id;
    private String nome;
    private Integer capacidadePassageiros;
    private String combustivel;
    private Boolean ativo = true;
    private List<VeiculoDTO> veiculos = new ArrayList<>();
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getCapacidadePassageiros() { return capacidadePassageiros; }
    public void setCapacidadePassageiros(Integer capacidadePassageiros) { this.capacidadePassageiros = capacidadePassageiros; }

    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public List<VeiculoDTO> getVeiculos() { return veiculos; }
    public void setVeiculos(List<VeiculoDTO> veiculos) { this.veiculos = veiculos; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}
