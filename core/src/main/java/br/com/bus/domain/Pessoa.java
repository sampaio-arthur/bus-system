package br.com.bus.domain;

import java.time.LocalDate;
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
@Table(name = "pessoa")
public class Pessoa extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "cnh", length = 20)
    private String cnh;

    @Column(name = "validade_cnh")
    private LocalDate validadeCnh;

    @Column(name = "categoria_cnh", length = 5)
    private String categoriaCnh;

    @Column(name = "especialidade", length = 120)
    private String especialidade;

    @OneToMany(mappedBy = "responsavel")
    private List<Manutencao> manutencoes = new ArrayList<>();

    @OneToMany(mappedBy = "motorista")
    private List<Viagem> viagens = new ArrayList<>();

    @OneToMany(mappedBy = "passageiro")
    private List<Passagem> passagens = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public LocalDate getValidadeCnh() { return validadeCnh; }
    public void setValidadeCnh(LocalDate validadeCnh) { this.validadeCnh = validadeCnh; }

    public String getCategoriaCnh() { return categoriaCnh; }
    public void setCategoriaCnh(String categoriaCnh) { this.categoriaCnh = categoriaCnh; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public List<Manutencao> getManutencoes() { return manutencoes; }
    public void setManutencoes(List<Manutencao> manutencoes) { this.manutencoes = manutencoes; }

    public List<Viagem> getViagens() { return viagens; }
    public void setViagens(List<Viagem> viagens) { this.viagens = viagens; }

    public List<Passagem> getPassagens() { return passagens; }
    public void setPassagens(List<Passagem> passagens) { this.passagens = passagens; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return id != null && Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
