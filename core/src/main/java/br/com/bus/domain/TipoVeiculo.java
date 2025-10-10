package br.com.bus.domain;

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
@Table(name = "tipo_veiculo")
public class TipoVeiculo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_veiculo")
    public Integer id;

    @Column(name = "descricao", length = 255, nullable = false)
    public String descricao;

    @OneToMany(mappedBy = "tipoVeiculo", fetch = FetchType.LAZY)
    public Set<Veiculo> veiculos = new LinkedHashSet<>();
}
