package br.com.bus.domain;

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
    public ProgressoViagemId id = new ProgressoViagemId();

    @MapsId("idPontoParada")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    public PontoParada pontoParada;

    @MapsId("idViagem")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    public Viagem viagem;
}
