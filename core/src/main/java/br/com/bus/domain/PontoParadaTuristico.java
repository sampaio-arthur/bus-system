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
    public Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    public PontoParada pontoParada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_turistico", nullable = false)
    public PontoTuristico pontoTuristico;
}
