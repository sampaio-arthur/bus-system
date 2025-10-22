package br.com.bus.application.dto;

public class PontoParadaTuristicoDTO {

	private Long id;
	private PontoParadaDTO pontoParada;
	private PontoTuristicoDTO pontoTuristico;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PontoParadaDTO getPontoParada() {
		return pontoParada;
	}
	public void setPontoParada(PontoParadaDTO pontoParada) {
		this.pontoParada = pontoParada;
	}
	public PontoTuristicoDTO getPontoTuristico() {
		return pontoTuristico;
	}
	public void setPontoTuristico(PontoTuristicoDTO pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}
	
}
