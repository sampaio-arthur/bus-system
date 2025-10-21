package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaTuristicoDTO;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoParadaTuristico;
import br.com.bus.domain.PontoTuristico;

public final class PontoParadaTuristicoMap {

    private PontoParadaTuristicoMap() {
    }

    public static PontoParadaTuristico toEntity(PontoParadaTuristicoDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoParadaTuristico entity = new PontoParadaTuristico();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PontoParadaTuristicoDTO toDTO(PontoParadaTuristico entity) {
        if (entity == null) {
            return null;
        }
        PontoParadaTuristicoDTO dto = new PontoParadaTuristicoDTO();
        dto.setId(entity.getId());
        dto.setPontoParada(PontoParadaMap.toSummary(entity.getPontoParada()));
        dto.setPontoTuristico(PontoTuristicoMap.toSummary(entity.getPontoTuristico()));
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
        if (dto.getPontoParada() != null && dto.getPontoParada().getId() != null) {
            PontoParada pontoParada = entity.getPontoParada();
            if (pontoParada == null) {
                pontoParada = new PontoParada();
            }
            pontoParada.setId(dto.getPontoParada().getId());
            entity.setPontoParada(pontoParada);
            entity.setId(dto.getPontoParada().getId());
        }
        if (dto.getPontoTuristico() != null && dto.getPontoTuristico().getId() != null) {
            PontoTuristico pontoTuristico = entity.getPontoTuristico();
            if (pontoTuristico == null) {
                pontoTuristico = new PontoTuristico();
            }
            pontoTuristico.setId(dto.getPontoTuristico().getId());
            entity.setPontoTuristico(pontoTuristico);
        } else if (dto.getPontoTuristico() == null) {
            entity.setPontoTuristico(null);
        }
    }

    public static PontoParadaTuristicoDTO toSummary(PontoParadaTuristico entity) {
        if (entity == null) {
            return null;
        }
        PontoParadaTuristicoDTO dto = new PontoParadaTuristicoDTO();
        dto.setId(entity.getId());
        return dto;
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

    public static Set<PontoParadaTuristicoDTO> toSummarySet(Set<PontoParadaTuristico> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PontoParadaTuristicoMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
