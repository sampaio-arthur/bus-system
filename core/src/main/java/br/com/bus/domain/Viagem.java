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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @NotNull
    @Column(name = "data_hora_saida", nullable = false)
    private LocalDateTime dataHoraSaida;

    @Column(name = "data_hora_chegada_prevista")
    private LocalDateTime dataHoraChegadaPrevista;

    @Column(name = "data_hora_chegada_real")
    private LocalDateTime dataHoraChegadaReal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Linha linha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Pessoa motorista;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_viagem_id")
    private StatusViagem statusViagem;

    @OneToMany(mappedBy = "viagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passagem> passagens = new ArrayList<>();

    @Version
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHoraSaida() { return dataHoraSaida; }
    public void setDataHoraSaida(LocalDateTime dataHoraSaida) { this.dataHoraSaida = dataHoraSaida; }

    public LocalDateTime getDataHoraChegadaPrevista() { return dataHoraChegadaPrevista; }
    public void setDataHoraChegadaPrevista(LocalDateTime dataHoraChegadaPrevista) { this.dataHoraChegadaPrevista = dataHoraChegadaPrevista; }

    public LocalDateTime getDataHoraChegadaReal() { return dataHoraChegadaReal; }
    public void setDataHoraChegadaReal(LocalDateTime dataHoraChegadaReal) { this.dataHoraChegadaReal = dataHoraChegadaReal; }

    public Linha getLinha() { return linha; }
    public void setLinha(Linha linha) { this.linha = linha; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public Pessoa getMotorista() { return motorista; }
    public void setMotorista(Pessoa motorista) { this.motorista = motorista; }

    public StatusViagem getStatusViagem() { return statusViagem; }
    public void setStatusViagem(StatusViagem statusViagem) { this.statusViagem = statusViagem; }

    public List<Passagem> getPassagens() { return passagens; }
    public void setPassagens(List<Passagem> passagens) { this.passagens = passagens; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viagem)) return false;
        Viagem viagem = (Viagem) o;
        return id != null && id.equals(viagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
