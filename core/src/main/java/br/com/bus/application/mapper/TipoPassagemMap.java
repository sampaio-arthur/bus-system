package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.TipoPassagemDTO;
import br.com.bus.domain.TipoPassagem;

public final class TipoPassagemMap {

    private TipoPassagemMap() {
    }

    public static TipoPassagem toEntity(TipoPassagemDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoPassagem entity = new TipoPassagem();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static TipoPassagemDTO toDTO(TipoPassagem entity) {
        if (entity == null) {
            return null;
        }
        TipoPassagemDTO dto = new TipoPassagemDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setPassagens(PassagemMap.toSummarySet(entity.getPassagens()));
        return dto;
    }

    public static void updateEntityFromDTO(TipoPassagemDTO dto, TipoPassagem entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(TipoPassagemDTO dto, TipoPassagem entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setDescricao(dto.getDescricao());
    }

    public static TipoPassagemDTO toSummary(TipoPassagem entity) {
        if (entity == null) {
            return null;
        }
        TipoPassagemDTO dto = new TipoPassagemDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    public static Set<TipoPassagemDTO> toDTOSet(Set<TipoPassagem> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(TipoPassagemMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<TipoPassagem> toEntitySet(Set<TipoPassagemDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(TipoPassagemMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<TipoPassagemDTO> toSummarySet(Set<TipoPassagem> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(TipoPassagemMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
