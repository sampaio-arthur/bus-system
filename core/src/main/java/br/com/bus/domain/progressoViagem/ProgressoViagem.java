package br.com.bus.domain.progressoViagem;

import br.com.bus.domain.PontoParada;
import br.com.bus.domain.Viagem;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "progresso_viagem")
public class ProgressoViagem extends PanacheEntityBase {

    @EmbeddedId
    private ProgressoViagemId id = new ProgressoViagemId();

    @MapsId("idPontoParada")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    private PontoParada pontoParada;

    @MapsId("idViagem")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

	public ProgressoViagemId getId() {
		return id;
	}

	public void setId(ProgressoViagemId id) {
		this.id = id;
	}

	public PontoParada getPontoParada() {
		return pontoParada;
	}

	public void setPontoParada(PontoParada pontoParada) {
		this.pontoParada = pontoParada;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
    
}
