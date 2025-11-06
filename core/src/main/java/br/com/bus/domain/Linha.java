package br.com.bus.domain;

import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "linha")
public class Linha extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da linha é obrigatório")
    @Size(max = 100)
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Código da linha é obrigatório")
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "tempo_percurso_estimado")
    private Integer tempoPercursoEstimado;

    @Version
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getTempoPercursoEstimado() {
        return tempoPercursoEstimado;
    }

    public void setTempoPercursoEstimado(Integer tempoPercursoEstimado) {
        this.tempoPercursoEstimado = tempoPercursoEstimado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Linha))
            return false;
        Linha linha = (Linha) o;
        return id != null && id.equals(linha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
