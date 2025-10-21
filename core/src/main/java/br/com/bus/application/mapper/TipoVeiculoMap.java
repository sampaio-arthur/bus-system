package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.TipoVeiculoDTO;
import br.com.bus.domain.TipoVeiculo;

public final class TipoVeiculoMap {

    private TipoVeiculoMap() {
    }

    public static TipoVeiculo toEntity(TipoVeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoVeiculo entity = new TipoVeiculo();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static TipoVeiculoDTO toDTO(TipoVeiculo entity) {
        if (entity == null) {
            return null;
        }
        TipoVeiculoDTO dto = new TipoVeiculoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setVeiculos(VeiculoMap.toSummarySet(entity.getVeiculos()));
        return dto;
    }

    public static void updateEntityFromDTO(TipoVeiculoDTO dto, TipoVeiculo entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(TipoVeiculoDTO dto, TipoVeiculo entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setDescricao(dto.getDescricao());
    }

    public static TipoVeiculoDTO toSummary(TipoVeiculo entity) {
        if (entity == null) {
            return null;
        }
        TipoVeiculoDTO dto = new TipoVeiculoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    public static Set<TipoVeiculoDTO> toDTOSet(Set<TipoVeiculo> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(TipoVeiculoMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<TipoVeiculo> toEntitySet(Set<TipoVeiculoDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(TipoVeiculoMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<TipoVeiculoDTO> toSummarySet(Set<TipoVeiculo> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(TipoVeiculoMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
