package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDTO {

	private Long id;
    private String placa;
    private String modelo;
    private String chassi;
    private Integer anoFabricacao;
    private Integer capacidade;
    private Boolean ativo = true;
    private TipoVeiculoDTO tipoVeiculo;
    private List<ViagemDTO> viagens = new ArrayList<>();
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public TipoVeiculoDTO getTipoVeiculo() { return tipoVeiculo; }
    public void setTipoVeiculo(TipoVeiculoDTO tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }

    public List<ViagemDTO> getViagens() { return viagens; }
    public void setViagens(List<ViagemDTO> viagens) { this.viagens = viagens; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

}