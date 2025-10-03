package br.com.bus.application.mapper;

import br.com.bus.application.dto.VanDTO;
import br.com.bus.domain.Van;

public final class VanMap {

    private VanMap() {
    }

    public static Van toEntity(VanDTO dto) {
        if (dto == null) {
            return null;
        }
        Van entity = new Van();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static VanDTO toDTO(Van entity) {
        if (entity == null) {
            return null;
        }
        VanDTO dto = new VanDTO();
        VeiculoMap.populateDTO(entity, dto);
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public static void updateEntityFromDTO(VanDTO dto, Van entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(VanDTO dto, Van entity) {
        VeiculoMap.entityFromDTO(dto, entity);
        entity.setCategoria(dto.getCategoria());
    }

    public static VanDTO toSummary(Van entity) {
        if (entity == null) {
            return null;
        }
        VanDTO dto = new VanDTO();
        VeiculoMapperHelper.copySummary(entity, dto);
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public static Van fromSummary(VanDTO dto) {
        if (dto == null) {
            return null;
        }
        Van entity = new Van();
        entity.setId(dto.getId());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }
}
