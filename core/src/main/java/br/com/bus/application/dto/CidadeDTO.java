package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class CidadeDTO {

    private Long id;
    private String nome;
    private String uf;
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

    public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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