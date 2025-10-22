package br.com.bus.domain.manutencaoPeca;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ManutencaoPecaId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_manutencao")
    private Long idManutencao;

    @Column(name = "id_peca")
    private Long idPeca;

    public ManutencaoPecaId() {
    }

    public ManutencaoPecaId(Long idManutencao, Long idPeca) {
        this.idManutencao = idManutencao;
        this.idPeca = idPeca;
    }

    public Long getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Long idManutencao) {
        this.idManutencao = idManutencao;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ManutencaoPecaId))
            return false;
        ManutencaoPecaId that = (ManutencaoPecaId) o;
        return Objects.equals(idManutencao, that.idManutencao)
                && Objects.equals(idPeca, that.idPeca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idManutencao, idPeca);
    }
}
