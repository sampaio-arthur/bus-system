package br.com.bus.application.mapper;

import br.com.bus.application.dto.CarroDTO;
import br.com.bus.domain.Carro;

public final class CarroMap {

    private CarroMap() {
    }

    public static Carro toEntity(CarroDTO dto) {
        if (dto == null) {
            return null;
        }
        Carro entity = new Carro();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static CarroDTO toDTO(Carro entity) {
        if (entity == null) {
            return null;
        }
        CarroDTO dto = new CarroDTO();
        VeiculoMap.populateDTO(entity, dto);
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public static void updateEntityFromDTO(CarroDTO dto, Carro entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(CarroDTO dto, Carro entity) {
        VeiculoMap.entityFromDTO(dto, entity);
        entity.setCategoria(dto.getCategoria());
    }

    public static CarroDTO toSummary(Carro entity) {
        if (entity == null) {
            return null;
        }
        CarroDTO dto = new CarroDTO();
        VeiculoMapperHelper.copySummary(entity, dto);
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public static Carro fromSummary(CarroDTO dto) {
        if (dto == null) {
            return null;
        }
        Carro entity = new Carro();
        entity.setId(dto.getId());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }
}
