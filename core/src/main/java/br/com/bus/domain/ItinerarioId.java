package br.com.bus.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItinerarioId implements Serializable {

    @Column(name = "id_linha")
    private Long linhaId;

    @Column(name = "id_ponto_parada")
    private Long pontoParadaId;

    public ItinerarioId() {
    }

    public ItinerarioId(Long linhaId, Long pontoParadaId) {
        this.linhaId = linhaId;
        this.pontoParadaId = pontoParadaId;
    }

    public Long getLinhaId() { return linhaId; }
    public void setLinhaId(Long linhaId) { this.linhaId = linhaId; }

    public Long getPontoParadaId() { return pontoParadaId; }
    public void setPontoParadaId(Long pontoParadaId) { this.pontoParadaId = pontoParadaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItinerarioId)) return false;
        ItinerarioId that = (ItinerarioId) o;
        return Objects.equals(linhaId, that.linhaId) &&
               Objects.equals(pontoParadaId, that.pontoParadaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linhaId, pontoParadaId);
    }
}
