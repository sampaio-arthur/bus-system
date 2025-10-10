package br.com.bus.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class PontoTuristicoDTO {

	private Integer id;
	private String nome;
	private String descricao;
	private Set<PontoParadaTuristicoDTO> pontosParada = new LinkedHashSet<>();
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Set<PontoParadaTuristicoDTO> getPontosParada() {
		return pontosParada;
	}
	public void setPontosParada(Set<PontoParadaTuristicoDTO> pontosParada) {
		this.pontosParada = pontosParada;
	}
	
}
