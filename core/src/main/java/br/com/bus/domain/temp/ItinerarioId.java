package br.com.bus.domain.temp;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItinerarioId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ordem")
    public Short ordem;

    @Column(name = "id_linha")
    public Integer idLinha;

    @Column(name = "id_ponto_parada")
    public Integer idPontoParada;

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
