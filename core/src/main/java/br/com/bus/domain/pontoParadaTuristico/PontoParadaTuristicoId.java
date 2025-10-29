package br.com.bus.domain.pontoParadaTuristico;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PontoParadaTuristicoId implements Serializable{

	private static final long serialVersionUID = 1L;

    @Column(name = "id_ponto_parada")
    private Long idPontoParada;

    @Column(name = "id_ponto_turistico")
    private Long idPontoTuristico;

    public PontoParadaTuristicoId() {
    }

    public PontoParadaTuristicoId(Long idPontoParada, Long idPontoTuristico) {
        this.idPontoParada = idPontoParada;
        this.idPontoTuristico = idPontoTuristico;
    }

	public Long getIdPontoParada() {
		return idPontoParada;
	}

	public void setIdPontoParada(Long idPontoParada) {
		this.idPontoParada = idPontoParada;
	}

	public Long getIdPontoTuristico() {
		return idPontoTuristico;
	}

	public void setIdPontoTuristico(Long idPontoTuristico) {
		this.idPontoTuristico = idPontoTuristico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPontoParada, idPontoTuristico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PontoParadaTuristicoId other = (PontoParadaTuristicoId) obj;
		return Objects.equals(idPontoParada, other.idPontoParada)
				&& Objects.equals(idPontoTuristico, other.idPontoTuristico);
	}
	
}
