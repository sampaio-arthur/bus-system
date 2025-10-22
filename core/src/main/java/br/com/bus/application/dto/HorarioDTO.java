package br.com.bus.application.dto;

import java.time.LocalTime;

public class HorarioDTO {

    private Long id;
    private LocalTime horaSaida;
    private LocalTime horaChegada;
    private String diasSemana;
    private LinhaDTO linha;
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalTime horaChegada) {
        this.horaChegada = horaChegada;
    }

    public String getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(String diasSemana) {
        this.diasSemana = diasSemana;
    }

    public LinhaDTO getLinha() {
        return linha;
    }

    public void setLinha(LinhaDTO linha) {
        this.linha = linha;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
