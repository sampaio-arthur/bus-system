package br.com.bus.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class TipoPassagemDTO {

	private Long id;
	private String descricao;
	private Set<PassagemDTO> passagens = new LinkedHashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Set<PassagemDTO> getPassagens() {
		return passagens;
	}
	public void setPassagens(Set<PassagemDTO> passagens) {
		this.passagens = passagens;
	}
	
}
