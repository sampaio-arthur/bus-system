package br.com.bus.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinhaDTO {

    private Integer id;
    private String nome;
    private String codigo;
    private CronogramaDTO cronograma;
    private Set<ItinerarioDTO> itinerarios = new LinkedHashSet<>();
    private Set<ViagemDTO> viagens = new LinkedHashSet<>();
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public CronogramaDTO getCronograma() {
		return cronograma;
	}
	public void setCronograma(CronogramaDTO cronograma) {
		this.cronograma = cronograma;
	}
	public Set<ItinerarioDTO> getItinerarios() {
		return itinerarios;
	}
	public void setItinerarios(Set<ItinerarioDTO> itinerarios) {
		this.itinerarios = itinerarios;
	}
	public Set<ViagemDTO> getViagens() {
		return viagens;
	}
	public void setViagens(Set<ViagemDTO> viagens) {
		this.viagens = viagens;
	}
    
}
