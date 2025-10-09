package br.com.bus.application.mapper;

import br.com.bus.application.dto.TarifaDTO;
import br.com.bus.domain.Tarifa;

public final class TarifaMap {

    private TarifaMap() {
    }

    public static Tarifa toEntity(TarifaDTO dto) {
        if (dto == null) {
            return null;
        }
        Tarifa entity = new Tarifa();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static TarifaDTO toDTO(Tarifa entity) {
        if (entity == null) {
            return null;
        }
        TarifaDTO dto = new TarifaDTO();
        dto.setId(entity.getId());
        dto.setValor(entity.getValor());
        dto.setDescricao(entity.getDescricao());
        dto.setAtivo(entity.getAtivo());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        dto.setVersion(entity.getVersion());
        return dto;
    }

    public static void updateEntityFromDTO(TarifaDTO dto, Tarifa entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(TarifaDTO dto, Tarifa entity) {
        entity.setValor(dto.getValor());
        entity.setDescricao(dto.getDescricao());
        entity.setAtivo(dto.getAtivo());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());
        entity.setLinha(LinhaMap.fromSummary(dto.getLinha()));
        entity.setVersion(dto.getVersion());
    }

    public static TarifaDTO toSummary(Tarifa entity) {
        if (entity == null) {
            return null;
        }
        TarifaDTO dto = new TarifaDTO();
        dto.setId(entity.getId());
        dto.setValor(entity.getValor());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    public static Tarifa fromSummary(TarifaDTO dto) {
        if (dto == null) {
            return null;
        }
        Tarifa entity = new Tarifa();
        entity.setId(dto.getId());
        entity.setValor(dto.getValor());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}
