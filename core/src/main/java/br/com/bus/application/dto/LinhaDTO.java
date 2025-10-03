package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class LinhaDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String cor;
    private Boolean ativo = true;
    private Integer tempoPercursoEstimado;
    private List<RotaDTO> rotas = new ArrayList<>();
    private List<ParadaLinhaDTO> paradasLinha = new ArrayList<>();
    private List<HorarioDTO> horarios = new ArrayList<>();
    private List<TarifaDTO> tarifas = new ArrayList<>();
    private List<FeedbackPassageiroDTO> feedbacks = new ArrayList<>();
    
    public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getTempoPercursoEstimado() {
        return tempoPercursoEstimado;
    }

    public void setTempoPercursoEstimado(Integer tempoPercursoEstimado) {
        this.tempoPercursoEstimado = tempoPercursoEstimado;
    }

    public List<RotaDTO> getRotas() {
        return rotas;
    }

    public void setRotas(List<RotaDTO> rotas) {
        this.rotas = rotas;
    }

    public List<ParadaLinhaDTO> getParadasLinha() {
        return paradasLinha;
    }

    public void setParadasLinha(List<ParadaLinhaDTO> paradasLinha) {
        this.paradasLinha = paradasLinha;
    }

    public List<HorarioDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }

    public List<TarifaDTO> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<TarifaDTO> tarifas) {
        this.tarifas = tarifas;
    }

    public List<FeedbackPassageiroDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackPassageiroDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }

}