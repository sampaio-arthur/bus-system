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
    private Integer id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "telefone", length = 255)
    private String telefone;

    @Column(name = "cnh", length = 11)
    private String cnh;

    @Column(name = "validade_cnh")
    private LocalDate validadeCnh;

    @Column(name = "categoria_cnh")
    private Short categoriaCnh;

    @Column(name = "especialidade", length = 255)
    private String especialidade;

    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY)
    private Set<Viagem> viagens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "passageiro", fetch = FetchType.LAZY)
    private Set<Passagem> passagens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "mecanico", fetch = FetchType.LAZY)
    private Set<Manutencao> manutencoes = new LinkedHashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public LocalDate getValidadeCnh() {
		return validadeCnh;
	}

	public void setValidadeCnh(LocalDate validadeCnh) {
		this.validadeCnh = validadeCnh;
	}

	public Short getCategoriaCnh() {
		return categoriaCnh;
	}

	public void setCategoriaCnh(Short categoriaCnh) {
		this.categoriaCnh = categoriaCnh;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public Set<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(Set<Viagem> viagens) {
		this.viagens = viagens;
	}

	public Set<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(Set<Passagem> passagens) {
		this.passagens = passagens;
	}

	public Set<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(Set<Manutencao> manutencoes) {
		this.manutencoes = manutencoes;
	}
    
}
