package br.com.bus.application.mapper;

import br.com.bus.application.dto.ParadaLinhaDTO;
import br.com.bus.domain.ParadaLinha;

public final class ParadaLinhaMap {

    private ParadaLinhaMap() {
    }

    public static ParadaLinha toEntity(ParadaLinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        ParadaLinha entity = new ParadaLinha();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static ParadaLinhaDTO toDTO(ParadaLinha entity) {
        if (entity == null) {
            return null;
        }
        ParadaLinhaDTO dto = new ParadaLinhaDTO();
        dto.setId(entity.getId());
        dto.setOrdem(entity.getOrdem());
        dto.setTempoParadaMinutos(entity.getTempoParadaMinutos());
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        dto.setPontoParada(PontoParadaMap.toSummary(entity.getPontoParada()));
        dto.setVersion(entity.getVersion());
        return dto;
    }

    public static void updateEntityFromDTO(ParadaLinhaDTO dto, ParadaLinha entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(ParadaLinhaDTO dto, ParadaLinha entity) {
        entity.setOrdem(dto.getOrdem());
        entity.setTempoParadaMinutos(dto.getTempoParadaMinutos());
        entity.setLinha(LinhaMap.fromSummary(dto.getLinha()));
        entity.setPontoParada(PontoParadaMap.fromSummary(dto.getPontoParada()));
        entity.setVersion(dto.getVersion());
    }

    public static ParadaLinhaDTO toSummary(ParadaLinha entity) {
        if (entity == null) {
            return null;
        }
        ParadaLinhaDTO dto = new ParadaLinhaDTO();
        dto.setId(entity.getId());
        dto.setOrdem(entity.getOrdem());
        return dto;
    }

    public static ParadaLinha fromSummary(ParadaLinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        ParadaLinha entity = new ParadaLinha();
        entity.setId(dto.getId());
        entity.setOrdem(dto.getOrdem());
        return entity;
    }
}
