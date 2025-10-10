package br.com.bus.domain.manutencaoPeca;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ManutencaoPecaId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_manutencao")
    private Integer idManutencao;

    @Column(name = "id_peca")
    private Integer idPeca;

    public ManutencaoPecaId() {
    }

    public ManutencaoPecaId(Integer idManutencao, Integer idPeca) {
        this.idManutencao = idManutencao;
        this.idPeca = idPeca;
    }

    public Integer getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Integer idManutencao) {
        this.idManutencao = idManutencao;
    }

    public Integer getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Integer idPeca) {
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
