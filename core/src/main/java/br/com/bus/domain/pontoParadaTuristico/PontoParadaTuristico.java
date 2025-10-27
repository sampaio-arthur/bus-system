package br.com.bus.domain.pontoParadaTuristico;

import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoTuristico;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_parada_turistico")
public class PontoParadaTuristico extends PanacheEntityBase {

	@EmbeddedId
    private PontoParadaTuristicoId id = new PontoParadaTuristicoId();

    @MapsId("idPontoParada")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    private PontoParada pontoParada;

    @MapsId("idPontoTuristico")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_turistico", nullable = false)
    private PontoTuristico pontoTuristico;

	public PontoParada getPontoParada() {
		return pontoParada;
	}

	public void setPontoParada(PontoParada pontoParada) {
		this.pontoParada = pontoParada;
	}

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}

	public PontoParadaTuristicoId getId() {
		return id;
	}

	public void setId(PontoParadaTuristicoId id) {
		this.id = id;
	}
    
}
