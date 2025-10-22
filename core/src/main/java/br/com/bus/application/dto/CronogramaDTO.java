package br.com.bus.application.dto;

import java.time.LocalTime;

public class CronogramaDTO {

	private Long id;
	private LinhaDTO linha;
	private LocalTime horaPartida;
	private Short tipoDia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LinhaDTO getLinha() {
		return linha;
	}
	public void setLinha(LinhaDTO linha) {
		this.linha = linha;
	}
	public LocalTime getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(LocalTime horaPartida) {
		this.horaPartida = horaPartida;
	}
	public Short getTipoDia() {
		return tipoDia;
	}
	public void setTipoDia(Short tipoDia) {
		this.tipoDia = tipoDia;
	}
	
}
