package br.com.bus.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProgressoViagemId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "id_ponto_parada")
    private Integer idPontoParada;

    @Column(name = "id_viagem")
    private Integer idViagem;
    
    public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Integer getIdPontoParada() {
		return idPontoParada;
	}

	public void setIdPontoParada(Integer idPontoParada) {
		this.idPontoParada = idPontoParada;
	}

	public Integer getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(Integer idViagem) {
		this.idViagem = idViagem;
	}

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
