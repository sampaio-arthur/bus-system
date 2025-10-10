package br.com.bus.domain;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "peca")
public class Peca extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peca")
    public Integer id;

    @Column(name = "valor_unitario")
    public BigDecimal valorUnitario;

    @Column(name = "nome", length = 255)
    public String nome;

    @Column(name = "fabricante", length = 255)
    public String fabricante;

    @Column(name = "quantidade")
    public Integer quantidade;

    @OneToMany(mappedBy = "peca", fetch = FetchType.LAZY)
    public Set<ManutencaoPeca> manutencoes = new LinkedHashSet<>();
}
