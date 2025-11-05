package br.com.bus.domain;

import java.time.LocalDateTime;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "viagem")
public class Viagem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_partida_real")
    private LocalDateTime dataPartidaReal;

    @NotNull
    @Column(name = "data_partida_prevista", nullable = false)
    private LocalDateTime dataPartidaPrevista;

    @NotNull
    @Column(name = "data_chegada_prevista", nullable = false)
    private LocalDateTime dataChegadaPrevista;

    @Column(name = "data_chegada_real")
    private LocalDateTime dataChegadaReal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "linha_id", nullable = false)
    private Linha linha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "motorista_id", nullable = false)
    private Pessoa motorista;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "viagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passagem> passagens = new ArrayList<>();

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPartidaReal() {
        return dataPartidaReal;
    }

    public void setDataPartidaReal(LocalDateTime dataPartidaReal) {
        this.dataPartidaReal = dataPartidaReal;
    }

    public LocalDateTime getDataPartidaPrevista() {
        return dataPartidaPrevista;
    }

    public void setDataPartidaPrevista(LocalDateTime dataPartidaPrevista) {
        this.dataPartidaPrevista = dataPartidaPrevista;
    }

    public LocalDateTime getDataChegadaPrevista() {
        return dataChegadaPrevista;
    }

    public void setDataChegadaPrevista(LocalDateTime dataChegadaPrevista) {
        this.dataChegadaPrevista = dataChegadaPrevista;
    }

    public LocalDateTime getDataChegadaReal() {
        return dataChegadaReal;
    }

    public void setDataChegadaReal(LocalDateTime dataChegadaReal) {
        this.dataChegadaReal = dataChegadaReal;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Pessoa getMotorista() {
        return motorista;
    }

    public void setMotorista(Pessoa motorista) {
        this.motorista = motorista;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Viagem))
            return false;
        Viagem viagem = (Viagem) o;
        return id != null && id.equals(viagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
