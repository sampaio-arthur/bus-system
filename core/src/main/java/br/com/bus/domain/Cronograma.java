package br.com.bus.domain;

import java.time.LocalTime;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cronograma")
public class Cronograma extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    private Linha linha;

    @Column(name = "hora_partida", nullable = false)
    private LocalTime horaPartida;

    @Column(name = "tipo_dia", length = 40)
    private String tipoDia;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Linha getLinha() { return linha; }
    public void setLinha(Linha linha) { this.linha = linha; }

    public LocalTime getHoraPartida() { return horaPartida; }
    public void setHoraPartida(LocalTime horaPartida) { this.horaPartida = horaPartida; }

    public String getTipoDia() { return tipoDia; }
    public void setTipoDia(String tipoDia) { this.tipoDia = tipoDia; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cronograma)) return false;
        Cronograma that = (Cronograma) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
