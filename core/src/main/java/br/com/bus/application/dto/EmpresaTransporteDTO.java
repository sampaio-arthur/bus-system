package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTransporteDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;
    private Boolean ativo = true;
    private int version;
    private List<MotoristaDTO> motoristas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<MotoristaDTO> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(List<MotoristaDTO> motoristas) {
        this.motoristas = motoristas;
    }

}