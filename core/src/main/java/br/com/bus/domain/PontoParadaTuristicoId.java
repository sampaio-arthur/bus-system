package br.com.bus.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PontoParadaTuristicoId implements Serializable {

    @Column(name = "id_ponto_parada")
    private Long pontoParadaId;

    @Column(name = "id_ponto_turistico")
    private Long pontoTuristicoId;

    public PontoParadaTuristicoId() {
    }

    public PontoParadaTuristicoId(Long pontoParadaId, Long pontoTuristicoId) {
        this.pontoParadaId = pontoParadaId;
        this.pontoTuristicoId = pontoTuristicoId;
    }

    public Long getPontoParadaId() { return pontoParadaId; }
    public void setPontoParadaId(Long pontoParadaId) { this.pontoParadaId = pontoParadaId; }

    public Long getPontoTuristicoId() { return pontoTuristicoId; }
    public void setPontoTuristicoId(Long pontoTuristicoId) { this.pontoTuristicoId = pontoTuristicoId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoParadaTuristicoId)) return false;
        PontoParadaTuristicoId that = (PontoParadaTuristicoId) o;
        return Objects.equals(pontoParadaId, that.pontoParadaId) &&
               Objects.equals(pontoTuristicoId, that.pontoTuristicoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pontoParadaId, pontoTuristicoId);
    }
}
