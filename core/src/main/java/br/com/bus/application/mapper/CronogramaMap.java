package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.CronogramaDTO;
import br.com.bus.domain.Cronograma;
import br.com.bus.domain.Linha;

public final class CronogramaMap {

    private CronogramaMap() {
    }

    public static Cronograma toEntity(CronogramaDTO dto) {
        if (dto == null) {
            return null;
        }
        Cronograma entity = new Cronograma();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static CronogramaDTO toDTO(Cronograma entity) {
        if (entity == null) {
            return null;
        }
        CronogramaDTO dto = new CronogramaDTO();
        dto.setId(entity.getId());
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        dto.setHoraPartida(entity.getHoraPartida());
        dto.setTipoDia(entity.getTipoDia());
        return dto;
    }

    public static void updateEntityFromDTO(CronogramaDTO dto, Cronograma entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(CronogramaDTO dto, Cronograma entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getLinha() != null && dto.getLinha().getId() != null) {
            Linha linha = entity.getLinha();
            if (linha == null) {
                linha = new Linha();
            }
            linha.setId(dto.getLinha().getId());
            entity.setLinha(linha);
        } else {
            entity.setLinha(null);
        }
        entity.setHoraPartida(dto.getHoraPartida());
        entity.setTipoDia(dto.getTipoDia());
    }

    public static CronogramaDTO toSummary(Cronograma entity) {
        if (entity == null) {
            return null;
        }
        CronogramaDTO dto = new CronogramaDTO();
        dto.setId(entity.getId());
        dto.setHoraPartida(entity.getHoraPartida());
        dto.setTipoDia(entity.getTipoDia());
        return dto;
    }

    public static Set<CronogramaDTO> toDTOSet(Set<Cronograma> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(CronogramaMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Cronograma> toEntitySet(Set<CronogramaDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(CronogramaMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<CronogramaDTO> toSummarySet(Set<Cronograma> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(CronogramaMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
