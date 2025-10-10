package br.com.bus.domain.temp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProgressoViagemId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "data")
    public LocalDateTime data;

    @Column(name = "id_ponto_parada")
    public Integer idPontoParada;

    @Column(name = "id_viagem")
    public Integer idViagem;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProgressoViagemId that = (ProgressoViagemId) o;
        return Objects.equals(data, that.data)
                && Objects.equals(idPontoParada, that.idPontoParada)
                && Objects.equals(idViagem, that.idViagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, idPontoParada, idViagem);
    }
}
