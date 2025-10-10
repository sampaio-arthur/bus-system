package br.com.bus.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class CidadeDTO {

    private Integer id;
    private String nome;
    private String uf;
    private Set<PontoParadaDTO> pontosParada = new LinkedHashSet<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Set<PontoParadaDTO> getPontosParada() {
		return pontosParada;
	}
	public void setPontosParada(Set<PontoParadaDTO> pontosParada) {
		this.pontosParada = pontosParada;
	}
    
}
