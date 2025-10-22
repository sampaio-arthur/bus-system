package br.com.bus.application.dto;

public class ParadaLinhaDTO {

    private Long id;
    private Integer ordem;
    private Integer tempoParadaMinutos;
    private LinhaDTO linha;
    private PontoParadaDTO pontoParada;
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Integer getTempoParadaMinutos() {
        return tempoParadaMinutos;
    }

    public void setTempoParadaMinutos(Integer tempoParadaMinutos) {
        this.tempoParadaMinutos = tempoParadaMinutos;
    }

    public LinhaDTO getLinha() {
        return linha;
    }

    public void setLinha(LinhaDTO linha) {
        this.linha = linha;
    }

    public PontoParadaDTO getPontoParada() {
        return pontoParada;
    }

    public void setPontoParada(PontoParadaDTO pontoParada) {
        this.pontoParada = pontoParada;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
