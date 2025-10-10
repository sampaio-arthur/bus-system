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
@Table(name = "ponto_turistico")
public class PontoTuristico extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto_turistico")
    public Integer id;

    @Column(name = "nome", length = 255)
    public String nome;

    @Column(name = "descricao", length = 255)
    public String descricao;

    @OneToMany(mappedBy = "pontoTuristico", fetch = FetchType.LAZY)
    public Set<PontoParadaTuristico> pontosParada = new LinkedHashSet<>();
}
