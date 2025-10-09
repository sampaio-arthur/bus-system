package br.com.bus.domain;

import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "itinerario")
public class Itinerario extends PanacheEntityBase {

    @EmbeddedId
    private ItinerarioId id = new ItinerarioId();

    @MapsId("linhaId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    private Linha linha;

    @MapsId("pontoParadaId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    private PontoParada pontoParada;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    public ItinerarioId getId() { return id; }
    public void setId(ItinerarioId id) { this.id = id; }

    public Linha getLinha() { return linha; }
    public void setLinha(Linha linha) { this.linha = linha; }

    public PontoParada getPontoParada() { return pontoParada; }
    public void setPontoParada(PontoParada pontoParada) { this.pontoParada = pontoParada; }

    public Integer getOrdem() { return ordem; }
    public void setOrdem(Integer ordem) { this.ordem = ordem; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Itinerario)) return false;
        Itinerario that = (Itinerario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
