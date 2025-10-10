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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_parada")
public class PontoParada extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto_parada")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", nullable = false)
    public Cidade cidade;

    @Column(name = "nome", length = 255)
    public String nome;

    @Column(name = "long", length = 255)
    public String longitude;

    @Column(name = "lat", length = 255)
    public String latitude;

    @OneToOne(mappedBy = "pontoParada", fetch = FetchType.LAZY)
    public PontoParadaTuristico pontoParadaTuristico;

    @OneToMany(mappedBy = "pontoParada", fetch = FetchType.LAZY)
    public Set<Itinerario> itinerarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pontoParada", fetch = FetchType.LAZY)
    public Set<ProgressoViagem> progressos = new LinkedHashSet<>();
}
