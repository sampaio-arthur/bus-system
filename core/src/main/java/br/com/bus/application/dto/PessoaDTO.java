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
    private String tipoPessoa;
    private String cnh;
    private LocalDateTime dataVencimentoCnh;
    private String categoriaCnh;
    private Integer anosExperiencia;
    private String numeroCarteirinha;
    private Boolean descontoEstudante = Boolean.FALSE;
    private Boolean descontoIdoso = Boolean.FALSE;
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

    public String getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public LocalDateTime getDataVencimentoCnh() { return dataVencimentoCnh; }
    public void setDataVencimentoCnh(LocalDateTime dataVencimentoCnh) { this.dataVencimentoCnh = dataVencimentoCnh; }

    public String getCategoriaCnh() { return categoriaCnh; }
    public void setCategoriaCnh(String categoriaCnh) { this.categoriaCnh = categoriaCnh; }

    public Integer getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(Integer anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public String getNumeroCarteirinha() { return numeroCarteirinha; }
    public void setNumeroCarteirinha(String numeroCarteirinha) { this.numeroCarteirinha = numeroCarteirinha; }

    public Boolean getDescontoEstudante() { return descontoEstudante; }
    public void setDescontoEstudante(Boolean descontoEstudante) { this.descontoEstudante = descontoEstudante; }

    public Boolean getDescontoIdoso() { return descontoIdoso; }
    public void setDescontoIdoso(Boolean descontoIdoso) { this.descontoIdoso = descontoIdoso; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}
