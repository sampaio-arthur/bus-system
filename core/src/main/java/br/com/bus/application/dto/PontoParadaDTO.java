package br.com.bus.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class PontoParadaDTO {

    private Integer id;
    private CidadeDTO cidade;
    private String nome;
    private String longitude;
    private String latitude;
    private PontoParadaTuristicoDTO pontoParadaTuristico;
    private Set<ItinerarioDTO> itinerarios = new LinkedHashSet<>();
    private Set<ProgressoViagemDTO> progressos = new LinkedHashSet<>();
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CidadeDTO getCidade() {
		return cidade;
	}
	public void setCidade(CidadeDTO cidade) {
		this.cidade = cidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public PontoParadaTuristicoDTO getPontoParadaTuristico() {
		return pontoParadaTuristico;
	}
	public void setPontoParadaTuristico(PontoParadaTuristicoDTO pontoParadaTuristico) {
		this.pontoParadaTuristico = pontoParadaTuristico;
	}
	public Set<ItinerarioDTO> getItinerarios() {
		return itinerarios;
	}
	public void setItinerarios(Set<ItinerarioDTO> itinerarios) {
		this.itinerarios = itinerarios;
	}
	public Set<ProgressoViagemDTO> getProgressos() {
		return progressos;
	}
	public void setProgressos(Set<ProgressoViagemDTO> progressos) {
		this.progressos = progressos;
	}
    
}
