package br.com.bus.application.dto;

import java.math.BigDecimal;

public class MediaPassageirosPorLinhaDTO {

    private String linha;
    private BigDecimal mediaPassageiros;

    public MediaPassageirosPorLinhaDTO() {}

    public MediaPassageirosPorLinhaDTO(String linha, BigDecimal mediaPassageiros) {
        this.linha = linha;
        this.mediaPassageiros = mediaPassageiros;
    }

    public String getLinha() { return linha; }
    public void setLinha(String linha) { this.linha = linha; }

    public BigDecimal getMediaPassageiros() { return mediaPassageiros; }
    public void setMediaPassageiros(BigDecimal mediaPassageiros) { this.mediaPassageiros = mediaPassageiros; }
}

