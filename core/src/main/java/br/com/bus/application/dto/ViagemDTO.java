package br.com.bus.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViagemDTO {

    private Long id;
    private LocalDateTime dataHoraSaida;
    private LocalDateTime dataHoraChegadaPrevista;
    private LocalDateTime dataHoraChegadaReal;
    private LinhaDTO linha;
    private VeiculoDTO veiculo;
    private PessoaDTO motorista;
    private StatusViagemDTO statusViagem;
    private List<PassagemDTO> passagens = new ArrayList<>();
    private int version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHoraSaida() { return dataHoraSaida; }
    public void setDataHoraSaida(LocalDateTime dataHoraSaida) { this.dataHoraSaida = dataHoraSaida; }

    public LocalDateTime getDataHoraChegadaPrevista() { return dataHoraChegadaPrevista; }
    public void setDataHoraChegadaPrevista(LocalDateTime dataHoraChegadaPrevista) { this.dataHoraChegadaPrevista = dataHoraChegadaPrevista; }

    public LocalDateTime getDataHoraChegadaReal() { return dataHoraChegadaReal; }
    public void setDataHoraChegadaReal(LocalDateTime dataHoraChegadaReal) { this.dataHoraChegadaReal = dataHoraChegadaReal; }

    public LinhaDTO getLinha() { return linha; }
    public void setLinha(LinhaDTO linha) { this.linha = linha; }

    public VeiculoDTO getVeiculo() { return veiculo; }
    public void setVeiculo(VeiculoDTO veiculo) { this.veiculo = veiculo; }

    public PessoaDTO getMotorista() { return motorista; }
    public void setMotorista(PessoaDTO motorista) { this.motorista = motorista; }

    public StatusViagemDTO getStatusViagem() { return statusViagem; }
    public void setStatusViagem(StatusViagemDTO statusViagem) { this.statusViagem = statusViagem; }

    public List<PassagemDTO> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemDTO> passagens) { this.passagens = passagens; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}
