package br.com.bus.domain;

import java.time.LocalDateTime;
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
@Table(name = "progresso_viagem")
public class ProgressoViagem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_parada", nullable = false)
    private PontoParada pontoParada;

    @Column(name = "data_progresso", nullable = false)
    private LocalDateTime dataProgresso;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Viagem getViagem() { return viagem; }
    public void setViagem(Viagem viagem) { this.viagem = viagem; }

    public PontoParada getPontoParada() { return pontoParada; }
    public void setPontoParada(PontoParada pontoParada) { this.pontoParada = pontoParada; }

    public LocalDateTime getDataProgresso() { return dataProgresso; }
    public void setDataProgresso(LocalDateTime dataProgresso) { this.dataProgresso = dataProgresso; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgressoViagem)) return false;
        ProgressoViagem that = (ProgressoViagem) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
