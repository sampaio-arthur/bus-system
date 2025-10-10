package br.com.bus.domain;

import java.math.BigDecimal;

import br.com.bus.domain.temp.ManutencaoPecaId;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "manutencao_peca")
public class ManutencaoPeca extends PanacheEntityBase {

    @EmbeddedId
    public ManutencaoPecaId id = new ManutencaoPecaId();

    @MapsId("idManutencao")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_manutencao", nullable = false)
    public Manutencao manutencao;

    @MapsId("idPeca")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_peca", nullable = false)
    public Peca peca;

    @Column(name = "quantidade_utilizada")
    public Integer quantidadeUtilizada;

    @Column(name = "valor_unitario")
    public BigDecimal valorUnitario;
}
