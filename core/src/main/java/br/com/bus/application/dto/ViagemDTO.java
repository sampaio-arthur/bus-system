package br.com.bus.application.dto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class ViagemDTO {

    private Integer id;
    private PessoaDTO motorista;
    private LinhaDTO linha;
    private VeiculoDTO veiculo;
    private LocalDateTime dataPartidaPrevista;
    private LocalDateTime dataChegadaPrevista;
    private LocalDateTime dataPartidaReal;
    private LocalDateTime dataChegadaReal;
    private Short status;
    private Set<ProgressoViagemDTO> progresso = new LinkedHashSet<>();
    private Set<PassagemDTO> passagens = new LinkedHashSet<>();
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PessoaDTO getMotorista() {
		return motorista;
	}
	public void setMotorista(PessoaDTO motorista) {
		this.motorista = motorista;
	}
	public LinhaDTO getLinha() {
		return linha;
	}
	public void setLinha(LinhaDTO linha) {
		this.linha = linha;
	}
	public VeiculoDTO getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(VeiculoDTO veiculo) {
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
	public Set<ProgressoViagemDTO> getProgresso() {
		return progresso;
	}
	public void setProgresso(Set<ProgressoViagemDTO> progresso) {
		this.progresso = progresso;
	}
	public Set<PassagemDTO> getPassagens() {
		return passagens;
	}
	public void setPassagens(Set<PassagemDTO> passagens) {
		this.passagens = passagens;
	}
    
}