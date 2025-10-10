package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.CidadeDTO;
import br.com.bus.domain.Cidade;

public final class CidadeMap {

    private CidadeMap() {
    }

    public static Cidade toEntity(CidadeDTO dto) {
        if (dto == null) {
            return null;
        }
        Cidade entity = new Cidade();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static CidadeDTO toDTO(Cidade entity) {
        if (entity == null) {
            return null;
        }
        CidadeDTO dto = new CidadeDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setUf(entity.getUf());
        dto.setPontosParada(PontoParadaMap.toSummarySet(entity.getPontosParada()));
        return dto;
    }

    public static void updateEntityFromDTO(CidadeDTO dto, Cidade entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(CidadeDTO dto, Cidade entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNome(dto.getNome());
        entity.setUf(dto.getUf());
    }

    public static CidadeDTO toSummary(Cidade entity) {
        if (entity == null) {
            return null;
        }
        CidadeDTO dto = new CidadeDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setUf(entity.getUf());
        return dto;
    }

    public static Set<CidadeDTO> toDTOSet(Set<Cidade> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(CidadeMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Cidade> toEntitySet(Set<CidadeDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(CidadeMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<CidadeDTO> toSummarySet(Set<Cidade> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(CidadeMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
