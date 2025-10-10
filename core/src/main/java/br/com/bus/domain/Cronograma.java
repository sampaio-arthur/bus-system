package br.com.bus.domain;

import java.time.LocalTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cronograma")
public class Cronograma extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cronograma")
    public Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false, unique = true)
    public Linha linha;

    @Column(name = "hora_partida")
    public LocalTime horaPartida;

    @Column(name = "tipo_dia")
    public Short tipoDia;
}
