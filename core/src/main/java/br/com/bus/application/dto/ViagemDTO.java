package br.com.bus.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class ViagemDTO {

    private Long id;
    private LocalDateTime dataPartidaReal;
    private LocalDateTime dataPartidaPrevista;
    private LocalDateTime dataChegadaPrevista;
    private LocalDateTime dataChegadaReal;
    private LinhaDTO linha;
    private VeiculoDTO veiculo;
    private PessoaDTO motorista;
    private Integer status;
    private List<PassagemDTO> passagens = new ArrayList<>();
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
	public LinhaDTO getLinha() { return linha; }
    public void setLinha(LinhaDTO linha) { this.linha = linha; }

    public VeiculoDTO getVeiculo() { return veiculo; }
    public void setVeiculo(VeiculoDTO veiculo) { this.veiculo = veiculo; }

    public PessoaDTO getMotorista() { return motorista; }
    public void setMotorista(PessoaDTO motorista) { this.motorista = motorista; }

    public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<PassagemDTO> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemDTO> passagens) { this.passagens = passagens; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}
