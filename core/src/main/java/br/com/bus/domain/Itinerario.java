package br.com.bus.domain;

import br.com.bus.domain.temp.ItinerarioId;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
    public ItinerarioId id = new ItinerarioId();

    @MapsId("idLinha")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    public Linha linha;

    @MapsId("idPontoParada")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    public PontoParada pontoParada;
}
