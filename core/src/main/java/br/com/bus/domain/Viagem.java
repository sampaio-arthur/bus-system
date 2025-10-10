package br.com.bus.domain;

import java.time.LocalDateTime;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "viagem")
public class Viagem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viagem")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    public Pessoa motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    public Linha linha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", nullable = false)
    public Veiculo veiculo;

    @Column(name = "data_partida_prevista")
    public LocalDateTime dataPartidaPrevista;

    @Column(name = "data_chegada_prevista")
    public LocalDateTime dataChegadaPrevista;

    @Column(name = "data_partida_real")
    public LocalDateTime dataPartidaReal;

    @Column(name = "data_chegada_real")
    public LocalDateTime dataChegadaReal;

    @Column(name = "status")
    public Short status;

    @OneToMany(mappedBy = "viagem", fetch = FetchType.LAZY)
    public Set<ProgressoViagem> progresso = new LinkedHashSet<>();

    @OneToMany(mappedBy = "viagem", fetch = FetchType.LAZY)
    public Set<Passagem> passagens = new LinkedHashSet<>();
}
