package br.com.bus.application.dto;

import java.time.LocalDateTime;

public class PessoaDTO {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataNascimento;
    private String endereco;
    private Boolean ativo = Boolean.TRUE;
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

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}