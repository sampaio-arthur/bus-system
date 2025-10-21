package br.com.bus.domain.itinerario;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItinerarioId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ordem")
    private Short ordem;

    @Column(name = "id_linha")
    private Integer idLinha;

    @Column(name = "id_ponto_parada")
    private Integer idPontoParada;
    
    public Short getOrdem() {
		return ordem;
	}

	public void setOrdem(Short ordem) {
		this.ordem = ordem;
	}

	public Integer getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(Integer idLinha) {
		this.idLinha = idLinha;
	}

	public Integer getIdPontoParada() {
		return idPontoParada;
	}

	public void setIdPontoParada(Integer idPontoParada) {
		this.idPontoParada = idPontoParada;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItinerarioId that = (ItinerarioId) o;
        return Objects.equals(ordem, that.ordem)
                && Objects.equals(idLinha, that.idLinha)
                && Objects.equals(idPontoParada, that.idPontoParada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordem, idLinha, idPontoParada);
    }
}
