package br.com.bus.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.bus.domain.progressoViagem.ProgressoViagem;
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
    @Column(name = "id_viagem")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linha", nullable = false)
    private Linha linha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    @Column(name = "data_partida_prevista")
    private LocalDateTime dataPartidaPrevista;

    @Column(name = "data_chegada_prevista")
    private LocalDateTime dataChegadaPrevista;

    @Column(name = "data_partida_real")
    private LocalDateTime dataPartidaReal;

    @Column(name = "data_chegada_real")
    private LocalDateTime dataChegadaReal;

    @Column(name = "status")
    private Short status;

    @OneToMany(mappedBy = "viagem", fetch = FetchType.LAZY)
    private Set<ProgressoViagem> progresso = new LinkedHashSet<>();

    @OneToMany(mappedBy = "viagem", fetch = FetchType.LAZY)
    private Set<Passagem> passagens = new LinkedHashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getMotorista() {
		return motorista;
	}

	public void setMotorista(Pessoa motorista) {
		this.motorista = motorista;
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

	public LocalDateTime getDataPartidaReal() {
		return dataPartidaReal;
	}

	public void setDataPartidaReal(LocalDateTime dataPartidaReal) {
		this.dataPartidaReal = dataPartidaReal;
	}

	public LocalDateTime getDataChegadaReal() {
		return dataChegadaReal;
	}

	public void setDataChegadaReal(LocalDateTime dataChegadaReal) {
		this.dataChegadaReal = dataChegadaReal;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Set<ProgressoViagem> getProgresso() {
		return progresso;
	}

	public void setProgresso(Set<ProgressoViagem> progresso) {
		this.progresso = progresso;
	}

	public Set<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(Set<Passagem> passagens) {
		this.passagens = passagens;
	}
    
}
