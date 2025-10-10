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
import jakarta.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    public Integer id;

    @Column(name = "nome", length = 255, nullable = false)
    public String nome;

    @Column(name = "uf", length = 2, nullable = false)
    public String uf;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
    public Set<PontoParada> pontosParada = new LinkedHashSet<>();
}
