package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.domain.PontoTuristico;

public final class PontoTuristicoMap {

    private PontoTuristicoMap() {
    }

    public static PontoTuristico toEntity(PontoTuristicoDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoTuristico entity = new PontoTuristico();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PontoTuristicoDTO toDTO(PontoTuristico entity) {
        if (entity == null) {
            return null;
        }
        PontoTuristicoDTO dto = new PontoTuristicoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setPontosParada(PontoParadaTuristicoMap.toDTOSet(entity.getPontosParada()));
        return dto;
    }

    public static void updateEntityFromDTO(PontoTuristicoDTO dto, PontoTuristico entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PontoTuristicoDTO dto, PontoTuristico entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
    }

    public static PontoTuristicoDTO toSummary(PontoTuristico entity) {
        if (entity == null) {
            return null;
        }
        PontoTuristicoDTO dto = new PontoTuristicoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    public static Set<PontoTuristicoDTO> toDTOSet(Set<PontoTuristico> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PontoTuristicoMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PontoTuristico> toEntitySet(Set<PontoTuristicoDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(PontoTuristicoMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PontoTuristicoDTO> toSummarySet(Set<PontoTuristico> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PontoTuristicoMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
