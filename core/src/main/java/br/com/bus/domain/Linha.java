package br.com.bus.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "linha")
public class Linha extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linha")
    public Integer id;

    @Column(name = "nome", length = 255, nullable = false)
    public String nome;

    @Column(name = "codigo", length = 255, nullable = false)
    public String codigo;

    @OneToOne(mappedBy = "linha", fetch = FetchType.LAZY)
    public Cronograma cronograma;

    @OneToMany(mappedBy = "linha", fetch = FetchType.LAZY)
    public Set<Itinerario> itinerarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "linha", fetch = FetchType.LAZY)
    public Set<Viagem> viagens = new LinkedHashSet<>();
}
