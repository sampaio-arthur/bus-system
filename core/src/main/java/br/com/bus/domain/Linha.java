package br.com.bus.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "linha")
public class Linha extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "codigo", nullable = false, length = 30, unique = true)
    private String codigo;

    @OneToMany(mappedBy = "linha")
    private List<Viagem> viagens = new ArrayList<>();

    @OneToMany(mappedBy = "linha")
    private List<Cronograma> cronogramas = new ArrayList<>();

    @OneToMany(mappedBy = "linha")
    private List<Itinerario> itinerarios = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public List<Viagem> getViagens() { return viagens; }
    public void setViagens(List<Viagem> viagens) { this.viagens = viagens; }

    public List<Cronograma> getCronogramas() { return cronogramas; }
    public void setCronogramas(List<Cronograma> cronogramas) { this.cronogramas = cronogramas; }

    public List<Itinerario> getItinerarios() { return itinerarios; }
    public void setItinerarios(List<Itinerario> itinerarios) { this.itinerarios = itinerarios; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Linha)) return false;
        Linha linha = (Linha) o;
        return id != null && Objects.equals(id, linha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
