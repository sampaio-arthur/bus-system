package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class TipoVeiculoDTO {

    private Long id;
    private String descricao;
    private Boolean ativo = true;
    private List<VeiculoDTO> veiculos = new ArrayList<>();
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public List<VeiculoDTO> getVeiculos() { return veiculos; }
    public void setVeiculos(List<VeiculoDTO> veiculos) { this.veiculos = veiculos; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
