package br.com.bus.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDTO extends PessoaDTO {

    private String cnh;
    private LocalDateTime dataVencimentoCnh;
    private String categoriaCnh;
    private Integer anosExperiencia;
    private EmpresaTransporteDTO empresa;
    private List<ViagemDTO> viagens = new ArrayList<>();
    private int version;

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public LocalDateTime getDataVencimentoCnh() { return dataVencimentoCnh; }
    public void setDataVencimentoCnh(LocalDateTime dataVencimentoCnh) { this.dataVencimentoCnh = dataVencimentoCnh; }

    public String getCategoriaCnh() { return categoriaCnh; }
    public void setCategoriaCnh(String categoriaCnh) { this.categoriaCnh = categoriaCnh; }

    public Integer getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(Integer anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public EmpresaTransporteDTO getEmpresa() { return empresa; }
    public void setEmpresa(EmpresaTransporteDTO empresa) { this.empresa = empresa; }

    public List<ViagemDTO> getViagens() { return viagens; }
    public void setViagens(List<ViagemDTO> viagens) { this.viagens = viagens; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}