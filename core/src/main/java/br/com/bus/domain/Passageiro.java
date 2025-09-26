package br.com.bus.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("PASSAGEIRO")
@Table(name = "passageiro")
public class Passageiro extends Pessoa {

    @Column(name = "numero_carteirinha", length = 20, unique = true)
    private String numeroCarteirinha;

    @NotNull
    @Column(name = "desconto_estudante", nullable = false)
    private Boolean descontoEstudante = Boolean.FALSE;

    @NotNull
    @Column(name = "desconto_idoso", nullable = false)
    private Boolean descontoIdoso = Boolean.FALSE;

    @OneToMany(mappedBy = "passageiro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passagem> passagens = new ArrayList<>();

    @OneToMany(mappedBy = "passageiro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FeedbackPassageiro> feedbacks = new ArrayList<>();

    @Version
    private int version;

    public String getNumeroCarteirinha() { return numeroCarteirinha; }
    public void setNumeroCarteirinha(String numeroCarteirinha) { this.numeroCarteirinha = numeroCarteirinha; }

    public Boolean getDescontoEstudante() { return descontoEstudante; }
    public void setDescontoEstudante(Boolean descontoEstudante) { this.descontoEstudante = descontoEstudante; }

    public Boolean getDescontoIdoso() { return descontoIdoso; }
    public void setDescontoIdoso(Boolean descontoIdoso) { this.descontoIdoso = descontoIdoso; }

    public List<Passagem> getPassagens() { return passagens; }
    public void setPassagens(List<Passagem> passagens) { this.passagens = passagens; }

    public List<FeedbackPassageiro> getFeedbacks() { return feedbacks; }
    public void setFeedbacks(List<FeedbackPassageiro> feedbacks) { this.feedbacks = feedbacks; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passageiro)) return false;
        if (!super.equals(o)) return false;
        Passageiro that = (Passageiro) o;
        return Objects.equals(numeroCarteirinha, that.numeroCarteirinha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroCarteirinha);
    }

    @Override
    public String getPersonType() {
        return "PASSAGEIRO";
    }
}
