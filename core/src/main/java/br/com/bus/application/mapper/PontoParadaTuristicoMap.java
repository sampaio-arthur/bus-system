package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaTuristicoDTO;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristico;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristicoId;

public final class PontoParadaTuristicoMap {

    private PontoParadaTuristicoMap() {
    }

    public static PontoParadaTuristico toEntity(PontoParadaTuristicoDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoParadaTuristico entity = new PontoParadaTuristico();
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PontoParadaTuristicoDTO toDTO(PontoParadaTuristico entity) {
        if (entity == null) {
            return null;
        }
        PontoParadaTuristicoDTO dto = new PontoParadaTuristicoDTO();
        if (entity.getId() != null) {
            dto.setIdPontoParada(entity.getId().getIdPontoParada());
            dto.setIdPontoTuristico(entity.getId().getIdPontoTuristico());
        }
        return dto;
    }

    public static void updateEntityFromDTO(PontoParadaTuristicoDTO dto, PontoParadaTuristico entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PontoParadaTuristicoDTO dto, PontoParadaTuristico entity) {
        if (dto == null || entity == null) {
            return;
        }
        PontoParadaTuristicoId id = entity.getId();
        if (id == null) {
            id = new PontoParadaTuristicoId();
            entity.setId(id);
        }

        id.setIdPontoParada(dto.getIdPontoParada());
        id.setIdPontoTuristico(dto.getIdPontoTuristico());
    }

    public static Set<PontoParadaTuristicoDTO> toDTOSet(Set<PontoParadaTuristico> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PontoParadaTuristicoMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PontoParadaTuristico> toEntitySet(Set<PontoParadaTuristicoDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(PontoParadaTuristicoMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
