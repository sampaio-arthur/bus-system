package br.com.bus.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("MOTORISTA")
@Table(name = "motorista")
public class Motorista extends Pessoa {

    @NotBlank
    @Column(name = "cnh", nullable = false, length = 11, unique = true)
    private String cnh;

    @Column(name = "data_vencimento_cnh")
    private LocalDateTime dataVencimentoCnh;

    @Column(name = "categoria_cnh", length = 10)
    private String categoriaCnh;

    @Column(name = "anos_experiencia")
    private Integer anosExperiencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaTransporte empresa;

    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<Viagem> viagens = new ArrayList<>();

    @Version
    private int version;

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public LocalDateTime getDataVencimentoCnh() { return dataVencimentoCnh; }
    public void setDataVencimentoCnh(LocalDateTime dataVencimentoCnh) { this.dataVencimentoCnh = dataVencimentoCnh; }

    public String getCategoriaCnh() { return categoriaCnh; }
    public void setCategoriaCnh(String categoriaCnh) { this.categoriaCnh = categoriaCnh; }

    public Integer getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(Integer anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public EmpresaTransporte getEmpresa() { return empresa; }
    public void setEmpresa(EmpresaTransporte empresa) { this.empresa = empresa; }

    public List<Viagem> getViagens() { return viagens; }
    public void setViagens(List<Viagem> viagens) { this.viagens = viagens; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Motorista)) return false;
        if (!super.equals(o)) return false;
        Motorista that = (Motorista) o;
        return cnh.equals(that.cnh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cnh);
    }

    @Override
    public String getPersonType() {
        return "MOTORISTA";
    }
}