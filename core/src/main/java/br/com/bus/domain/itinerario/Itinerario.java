package br.com.bus.domain.itinerario;

import br.com.bus.domain.Linha;
import br.com.bus.domain.PontoParada;
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
    private ItinerarioId id = new ItinerarioId();

    @MapsId("idLinha")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    private Linha linha;

    @MapsId("idPontoParada")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    private PontoParada pontoParada;

	public ItinerarioId getId() {
		return id;
	}

	public void setId(ItinerarioId id) {
		this.id = id;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	public PontoParada getPontoParada() {
		return pontoParada;
	}

	public void setPontoParada(PontoParada pontoParada) {
		this.pontoParada = pontoParada;
	}
    
}
