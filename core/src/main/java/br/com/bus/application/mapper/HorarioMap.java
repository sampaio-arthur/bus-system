package br.com.bus.application.mapper;

import br.com.bus.application.dto.HorarioDTO;
import br.com.bus.domain.Horario;

public final class HorarioMap {

    private HorarioMap() {
    }

    public static Horario toEntity(HorarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Horario entity = new Horario();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static HorarioDTO toDTO(Horario entity) {
        if (entity == null) {
            return null;
        }
        HorarioDTO dto = new HorarioDTO();
        dto.setId(entity.getId());
        dto.setHoraSaida(entity.getHoraSaida());
        dto.setHoraChegada(entity.getHoraChegada());
        dto.setDiasSemana(entity.getDiasSemana());
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        dto.setVersion(entity.getVersion());
        return dto;
    }

    public static void updateEntityFromDTO(HorarioDTO dto, Horario entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(HorarioDTO dto, Horario entity) {
        entity.setHoraSaida(dto.getHoraSaida());
        entity.setHoraChegada(dto.getHoraChegada());
        entity.setDiasSemana(dto.getDiasSemana());
        entity.setLinha(LinhaMap.fromSummary(dto.getLinha()));
        entity.setVersion(dto.getVersion());
    }

    public static HorarioDTO toSummary(Horario entity) {
        if (entity == null) {
            return null;
        }
        HorarioDTO dto = new HorarioDTO();
        dto.setId(entity.getId());
        dto.setHoraSaida(entity.getHoraSaida());
        dto.setHoraChegada(entity.getHoraChegada());
        return dto;
    }

    public static Horario fromSummary(HorarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Horario entity = new Horario();
        entity.setId(dto.getId());
        entity.setHoraSaida(dto.getHoraSaida());
        entity.setHoraChegada(dto.getHoraChegada());
        return entity;
    }
}
