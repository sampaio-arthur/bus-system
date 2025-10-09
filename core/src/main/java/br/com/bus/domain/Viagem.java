package br.com.bus.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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

@Entity
@Table(name = "viagem")
public class Viagem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    private Linha linha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    @Column(name = "data_partida_real")
    private LocalDateTime dataPartidaReal;

    @Column(name = "data_chegada_prevista")
    private LocalDateTime dataChegadaPrevista;

    @Column(name = "status", length = 40)
    private String status;

    @Column(name = "data_partida_prevista")
    private LocalDateTime dataPartidaPrevista;

    @OneToMany(mappedBy = "viagem")
    private List<ProgressoViagem> progressos = new ArrayList<>();

    @OneToMany(mappedBy = "viagem")
    private List<Passagem> passagens = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Linha getLinha() { return linha; }
    public void setLinha(Linha linha) { this.linha = linha; }

    public Pessoa getMotorista() { return motorista; }
    public void setMotorista(Pessoa motorista) { this.motorista = motorista; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public LocalDateTime getDataPartidaReal() { return dataPartidaReal; }
    public void setDataPartidaReal(LocalDateTime dataPartidaReal) { this.dataPartidaReal = dataPartidaReal; }

    public LocalDateTime getDataChegadaPrevista() { return dataChegadaPrevista; }
    public void setDataChegadaPrevista(LocalDateTime dataChegadaPrevista) { this.dataChegadaPrevista = dataChegadaPrevista; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataPartidaPrevista() { return dataPartidaPrevista; }
    public void setDataPartidaPrevista(LocalDateTime dataPartidaPrevista) { this.dataPartidaPrevista = dataPartidaPrevista; }

    public List<ProgressoViagem> getProgressos() { return progressos; }
    public void setProgressos(List<ProgressoViagem> progressos) { this.progressos = progressos; }

    public List<Passagem> getPassagens() { return passagens; }
    public void setPassagens(List<Passagem> passagens) { this.passagens = passagens; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viagem)) return false;
        Viagem viagem = (Viagem) o;
        return id != null && Objects.equals(id, viagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
