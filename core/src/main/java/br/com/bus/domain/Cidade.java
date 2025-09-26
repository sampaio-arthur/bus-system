package br.com.bus.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cidade")
public class Cidade extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotBlank(message = "Estado não pode ser vazio")
    @Size(min = 2, max = 2)
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Size(max = 9)
    @Column(name = "cep", length = 9)
    private String cep;

    @Column(name = "populacao")
    private Long populacao;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PontoParada> pontosParada = new ArrayList<>();

    @Version
    private int version;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<PontoParada> getPontosParada() {
        return pontosParada;
    }

    public void setPontosParada(List<PontoParada> pontosParada) {
        this.pontosParada = pontosParada;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cidade))
            return false;
        Cidade that = (Cidade) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}