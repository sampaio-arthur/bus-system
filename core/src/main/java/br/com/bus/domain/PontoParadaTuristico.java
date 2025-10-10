package br.com.bus.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_parada_turistico")
public class PontoParadaTuristico extends PanacheEntityBase {

    @Id
    @Column(name = "id_ponto_parada")
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    private PontoParada pontoParada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_turistico", nullable = false)
    private PontoTuristico pontoTuristico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
    
}
