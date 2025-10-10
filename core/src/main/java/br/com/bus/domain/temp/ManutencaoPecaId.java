package br.com.bus.domain.temp;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ManutencaoPecaId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_manutencao")
    public Integer idManutencao;

    @Column(name = "id_peca")
    public Integer idPeca;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManutencaoPecaId that = (ManutencaoPecaId) o;
        return Objects.equals(idManutencao, that.idManutencao)
                && Objects.equals(idPeca, that.idPeca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idManutencao, idPeca);
    }
}
