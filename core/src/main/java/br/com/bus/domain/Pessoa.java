package br.com.bus.domain;

import java.time.LocalDate;
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
@Table(name = "pessoa")
public class Pessoa extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    public Integer id;

    @Column(name = "nome", length = 255, nullable = false)
    public String nome;

    @Column(name = "data_nascimento")
    public LocalDate dataNascimento;

    @Column(name = "email", length = 255)
    public String email;

    @Column(name = "cpf", length = 11)
    public String cpf;

    @Column(name = "telefone", length = 255)
    public String telefone;

    @Column(name = "cnh", length = 11)
    public String cnh;

    @Column(name = "validade_cnh")
    public LocalDate validadeCnh;

    @Column(name = "categoria_cnh")
    public Short categoriaCnh;

    @Column(name = "especialidade", length = 255)
    public String especialidade;

    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY)
    public Set<Viagem> viagens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "passageiro", fetch = FetchType.LAZY)
    public Set<Passagem> passagens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "mecanico", fetch = FetchType.LAZY)
    public Set<Manutencao> manutencoes = new LinkedHashSet<>();
}
