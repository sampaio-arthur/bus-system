package br.com.bus.domain;

import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "parada_linha")
public class ParadaLinha extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer ordem;

    private Integer tempoParadaMinutos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Linha linha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PontoParada pontoParada;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Integer getTempoParadaMinutos() {
        return tempoParadaMinutos;
    }

    public void setTempoParadaMinutos(Integer tempoParadaMinutos) {
        this.tempoParadaMinutos = tempoParadaMinutos;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public PontoParada getPontoParada() {
        return pontoParada;
    }

    public void setPontoParada(PontoParada pontoParada) {
        this.pontoParada = pontoParada;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ParadaLinha))
            return false;
        ParadaLinha that = (ParadaLinha) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
