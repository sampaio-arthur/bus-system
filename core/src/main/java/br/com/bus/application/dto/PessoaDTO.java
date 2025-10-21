package br.com.bus.application.dto;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class PessoaDTO {

	private Integer id;
	private String nome;
	private LocalDate dataNascimento;
	private String email;
	private String cpf;
	private String telefone;
	private String cnh;
	private LocalDate validadeCnh;
	private Short categoriaCnh;
	private String especialidade;
	private Set<ViagemDTO> viagens = new LinkedHashSet<>();
	private Set<PassagemDTO> passagens = new LinkedHashSet<>();
	private Set<ManutencaoDTO> manutencoes = new LinkedHashSet<>();
	
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
	public Set<ViagemDTO> getViagens() {
		return viagens;
	}
	public void setViagens(Set<ViagemDTO> viagens) {
		this.viagens = viagens;
	}
	public Set<PassagemDTO> getPassagens() {
		return passagens;
	}
	public void setPassagens(Set<PassagemDTO> passagens) {
		this.passagens = passagens;
	}
	public Set<ManutencaoDTO> getManutencoes() {
		return manutencoes;
	}
	public void setManutencoes(Set<ManutencaoDTO> manutencoes) {
		this.manutencoes = manutencoes;
	}
	
}
