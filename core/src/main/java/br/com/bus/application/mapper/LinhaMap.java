package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.LinhaDTO;
import br.com.bus.domain.Linha;

public final class LinhaMap {

    private LinhaMap() {
    }

    public static Linha toEntity(LinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        Linha entity = new Linha();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static LinhaDTO toDTO(Linha entity) {
        if (entity == null) {
            return null;
        }
        LinhaDTO dto = new LinhaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setCronograma(CronogramaMap.toSummary(entity.getCronograma()));
        dto.setItinerarios(ItinerarioMap.toDTOSet(entity.getItinerarios()));
        dto.setViagens(ViagemMap.toSummarySet(entity.getViagens()));
        return dto;
    }

    public static void updateEntityFromDTO(LinhaDTO dto, Linha entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(LinhaDTO dto, Linha entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
    }

    public static LinhaDTO toSummary(Linha entity) {
        if (entity == null) {
            return null;
        }
        LinhaDTO dto = new LinhaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    public static Set<LinhaDTO> toDTOSet(Set<Linha> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(LinhaMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Linha> toEntitySet(Set<LinhaDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(LinhaMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<LinhaDTO> toSummarySet(Set<Linha> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(LinhaMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
