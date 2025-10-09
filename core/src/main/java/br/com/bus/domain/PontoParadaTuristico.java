package br.com.bus.domain;

import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_parada_turistico")
public class PontoParadaTuristico extends PanacheEntityBase {

    @EmbeddedId
    private PontoParadaTuristicoId id = new PontoParadaTuristicoId();

    @MapsId("pontoParadaId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    private PontoParada pontoParada;

    @MapsId("pontoTuristicoId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_turistico", nullable = false)
    private PontoTuristico pontoTuristico;

    public PontoParadaTuristicoId getId() { return id; }
    public void setId(PontoParadaTuristicoId id) { this.id = id; }

    public PontoParada getPontoParada() { return pontoParada; }
    public void setPontoParada(PontoParada pontoParada) { this.pontoParada = pontoParada; }

    public PontoTuristico getPontoTuristico() { return pontoTuristico; }
    public void setPontoTuristico(PontoTuristico pontoTuristico) { this.pontoTuristico = pontoTuristico; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoParadaTuristico)) return false;
        PontoParadaTuristico that = (PontoParadaTuristico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
