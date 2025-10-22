package br.com.bus.application.mapper;

import br.com.bus.application.dto.OnibusDTO;
import br.com.bus.domain.Onibus;

public final class OnibusMap {

    private OnibusMap() {
    }

    public static Onibus toEntity(OnibusDTO dto) {
        if (dto == null) {
            return null;
        }
        Onibus entity = new Onibus();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static OnibusDTO toDTO(Onibus entity) {
        if (entity == null) {
            return null;
        }
        OnibusDTO dto = new OnibusDTO();
        VeiculoMap.populateDTO(entity, dto);
        dto.setNumeroLinha(entity.getNumeroLinha());
        dto.setTemArCondicionado(entity.getTemArCondicionado());
        dto.setAcessivelCadeirante(entity.getAcessivelCadeirante());
        return dto;
    }

    public static void updateEntityFromDTO(OnibusDTO dto, Onibus entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(OnibusDTO dto, Onibus entity) {
        VeiculoMap.entityFromDTO(dto, entity);
        entity.setNumeroLinha(dto.getNumeroLinha());
        entity.setTemArCondicionado(dto.getTemArCondicionado());
        entity.setAcessivelCadeirante(dto.getAcessivelCadeirante());
    }

    public static OnibusDTO toSummary(Onibus entity) {
        if (entity == null) {
            return null;
        }
        OnibusDTO dto = new OnibusDTO();
        VeiculoMapperHelper.copySummary(entity, dto);
        dto.setNumeroLinha(entity.getNumeroLinha());
        return dto;
    }

    public static Onibus fromSummary(OnibusDTO dto) {
        if (dto == null) {
            return null;
        }
        Onibus entity = new Onibus();
        entity.setId(dto.getId());
        entity.setNumeroLinha(dto.getNumeroLinha());
        entity.setTemArCondicionado(dto.getTemArCondicionado());
        entity.setAcessivelCadeirante(dto.getAcessivelCadeirante());
        return entity;
    }
}
