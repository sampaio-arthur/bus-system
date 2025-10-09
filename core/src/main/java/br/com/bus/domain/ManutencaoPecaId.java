package br.com.bus.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ManutencaoPecaId implements Serializable {

    @Column(name = "id_manutencao")
    private Long manutencaoId;

    @Column(name = "id_peca")
    private Long pecaId;

    public ManutencaoPecaId() {
    }

    public ManutencaoPecaId(Long manutencaoId, Long pecaId) {
        this.manutencaoId = manutencaoId;
        this.pecaId = pecaId;
    }

    public Long getManutencaoId() { return manutencaoId; }
    public void setManutencaoId(Long manutencaoId) { this.manutencaoId = manutencaoId; }

    public Long getPecaId() { return pecaId; }
    public void setPecaId(Long pecaId) { this.pecaId = pecaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManutencaoPecaId)) return false;
        ManutencaoPecaId that = (ManutencaoPecaId) o;
        return Objects.equals(manutencaoId, that.manutencaoId) &&
               Objects.equals(pecaId, that.pecaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manutencaoId, pecaId);
    }
}
