package br.com.bus.application.dto;

import java.util.ArrayList;
import java.util.List;

public class PassageiroDTO extends PessoaDTO {

    private String numeroCarteirinha;
    private Boolean descontoEstudante = Boolean.FALSE;
    private Boolean descontoIdoso = Boolean.FALSE;
    private List<PassagemDTO> passagens = new ArrayList<>();
    private List<FeedbackPassageiroDTO> feedbacks = new ArrayList<>();
    private int version;

    public String getNumeroCarteirinha() { return numeroCarteirinha; }
    public void setNumeroCarteirinha(String numeroCarteirinha) { this.numeroCarteirinha = numeroCarteirinha; }

    public Boolean getDescontoEstudante() { return descontoEstudante; }
    public void setDescontoEstudante(Boolean descontoEstudante) { this.descontoEstudante = descontoEstudante; }

    public Boolean getDescontoIdoso() { return descontoIdoso; }
    public void setDescontoIdoso(Boolean descontoIdoso) { this.descontoIdoso = descontoIdoso; }

    public List<PassagemDTO> getPassagens() { return passagens; }
    public void setPassagens(List<PassagemDTO> passagens) { this.passagens = passagens; }

    public List<FeedbackPassageiroDTO> getFeedbacks() { return feedbacks; }
    public void setFeedbacks(List<FeedbackPassageiroDTO> feedbacks) { this.feedbacks = feedbacks; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

}
