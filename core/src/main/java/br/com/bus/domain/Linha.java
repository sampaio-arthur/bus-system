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

    @Column(length = 7)
    private String cor;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    private Integer tempoPercursoEstimado;

    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Rota> rotas = new ArrayList<>();

    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ParadaLinha> paradasLinha = new ArrayList<>();

    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Horario> horarios = new ArrayList<>();

    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarifa> tarifas = new ArrayList<>();

    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FeedbackPassageiro> feedbacks = new ArrayList<>();

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    public List<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(List<Rota> rotas) {
        this.rotas = rotas;
    }

    public List<ParadaLinha> getParadasLinha() {
        return paradasLinha;
    }

    public void setParadasLinha(List<ParadaLinha> paradasLinha) {
        this.paradasLinha = paradasLinha;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public List<FeedbackPassageiro> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackPassageiro> feedbacks) {
        this.feedbacks = feedbacks;
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