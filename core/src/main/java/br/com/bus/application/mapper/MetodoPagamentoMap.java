package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.MetodoPagamentoDTO;
import br.com.bus.domain.MetodoPagamento;

public final class MetodoPagamentoMap {

    private MetodoPagamentoMap() {
    }

    public static MetodoPagamento toEntity(MetodoPagamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        MetodoPagamento entity = new MetodoPagamento();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static MetodoPagamentoDTO toDTO(MetodoPagamento entity) {
        if (entity == null) {
            return null;
        }
        MetodoPagamentoDTO dto = new MetodoPagamentoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setPassagens(PassagemMap.toSummarySet(entity.getPassagens()));
        return dto;
    }

    public static void updateEntityFromDTO(MetodoPagamentoDTO dto, MetodoPagamento entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(MetodoPagamentoDTO dto, MetodoPagamento entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setDescricao(dto.getDescricao());
    }

    public static MetodoPagamentoDTO toSummary(MetodoPagamento entity) {
        if (entity == null) {
            return null;
        }
        MetodoPagamentoDTO dto = new MetodoPagamentoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    public static Set<MetodoPagamentoDTO> toDTOSet(Set<MetodoPagamento> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(MetodoPagamentoMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<MetodoPagamento> toEntitySet(Set<MetodoPagamentoDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(MetodoPagamentoMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<MetodoPagamentoDTO> toSummarySet(Set<MetodoPagamento> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(MetodoPagamentoMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
