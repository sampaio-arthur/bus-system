package br.com.bus.application.dto;

import java.time.LocalDateTime;

public class FeedbackPassageiroDTO {

    private Long id;
    private Integer nota;
    private String comentario;
    private LocalDateTime dataFeedback = LocalDateTime.now();
    private PassageiroDTO passageiro;
    private LinhaDTO linha;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getDataFeedback() { return dataFeedback; }
    public void setDataFeedback(LocalDateTime dataFeedback) { this.dataFeedback = dataFeedback; }

    public PassageiroDTO getPassageiro() { return passageiro; }
    public void setPassageiro(PassageiroDTO passageiro) { this.passageiro = passageiro; }

    public LinhaDTO getLinha() { return linha; }
    public void setLinha(LinhaDTO linha) { this.linha = linha; }

}
