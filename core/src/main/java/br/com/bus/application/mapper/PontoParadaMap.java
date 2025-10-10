package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.domain.Cidade;
import br.com.bus.domain.PontoParada;

public final class PontoParadaMap {

    private PontoParadaMap() {
    }

    public static PontoParada toEntity(PontoParadaDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoParada entity = new PontoParada();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PontoParadaDTO toDTO(PontoParada entity) {
        if (entity == null) {
            return null;
        }
        PontoParadaDTO dto = new PontoParadaDTO();
        dto.setId(entity.getId());
        dto.setCidade(CidadeMap.toSummary(entity.getCidade()));
        dto.setNome(entity.getNome());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        dto.setPontoParadaTuristico(PontoParadaTuristicoMap.toSummary(entity.getPontoParadaTuristico()));
        dto.setItinerarios(ItinerarioMap.toDTOSet(entity.getItinerarios()));
        dto.setProgressos(ProgressoViagemMap.toDTOSet(entity.getProgressos()));
        return dto;
    }

    public static void updateEntityFromDTO(PontoParadaDTO dto, PontoParada entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PontoParadaDTO dto, PontoParada entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getCidade() != null && dto.getCidade().getId() != null) {
            Cidade cidade = entity.getCidade();
            if (cidade == null) {
                cidade = new Cidade();
            }
            cidade.setId(dto.getCidade().getId());
            entity.setCidade(cidade);
        } else {
            entity.setCidade(null);
        }
        entity.setNome(dto.getNome());
        entity.setLongitude(dto.getLongitude());
        entity.setLatitude(dto.getLatitude());
    }

    public static PontoParadaDTO toSummary(PontoParada entity) {
        if (entity == null) {
            return null;
        }
        PontoParadaDTO dto = new PontoParadaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        return dto;
    }

    public static Set<PontoParadaDTO> toDTOSet(Set<PontoParada> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PontoParadaMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PontoParada> toEntitySet(Set<PontoParadaDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(PontoParadaMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PontoParadaDTO> toSummarySet(Set<PontoParada> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PontoParadaMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
