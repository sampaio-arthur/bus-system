package br.com.bus.application.dto;

import java.time.LocalDateTime;

public class PessoaDTO {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataNascimento;
    private Boolean ativo = Boolean.TRUE;
    private String tipoPessoa;
    private String cnh;
    private LocalDateTime validadeCnh;
    private String categoriaCnh;
    private String numeroCarteirinha;
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public LocalDateTime getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDateTime dataNascimento) { this.dataNascimento = dataNascimento; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public String getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public String getCategoriaCnh() { return categoriaCnh; }
    public void setCategoriaCnh(String categoriaCnh) { this.categoriaCnh = categoriaCnh; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
	public String getNumeroCarteirinha() {
		return numeroCarteirinha;
	}
	public void setNumeroCarteirinha(String numeroCarteirinha) {
		this.numeroCarteirinha = numeroCarteirinha;
	}
	public LocalDateTime getValidadeCnh() {
		return validadeCnh;
	}
	public void setValidadeCnh(LocalDateTime validadeCnh) {
		this.validadeCnh = validadeCnh;
	}

}
