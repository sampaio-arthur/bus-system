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
@Table(name = "ponto_turistico")
public class PontoTuristico extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @OneToMany(mappedBy = "pontoTuristico")
    private List<PontoParadaTuristico> paradas = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<PontoParadaTuristico> getParadas() { return paradas; }
    public void setParadas(List<PontoParadaTuristico> paradas) { this.paradas = paradas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoTuristico)) return false;
        PontoTuristico that = (PontoTuristico) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
