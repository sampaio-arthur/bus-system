package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class StatusViagemDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo = true;
    private List<ViagemDTO> viagens = new ArrayList<>();
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public List<ViagemDTO> getViagens() { return viagens; }
    public void setViagens(List<ViagemDTO> viagens) { this.viagens = viagens; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}
