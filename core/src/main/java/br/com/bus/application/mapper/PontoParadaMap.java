package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoTuristico;

public final class PontoParadaMap {

    private PontoParadaMap() {
    }

    public static PontoParada toEntity(PontoParadaDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoParada entity = new PontoParada();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PontoParadaDTO toDTO(PontoParada entity) {
        if (entity == null) {
            return null;
        }
        PontoParadaDTO dto = new PontoParadaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setAtivo(entity.getAtivo());
        dto.setCidade(CidadeMap.toSummary(entity.getCidade()));
        dto.setVersion(entity.getVersion());
        if (entity.getPontosTuristicosProximos() != null) {
            List<PontoTuristicoDTO> pontos = entity.getPontosTuristicosProximos().stream()
                    .map(PontoTuristicoMap::toSummary)
                    .collect(Collectors.toList());
            dto.setPontosTuristicosProximos(pontos);
        }
        return dto;
    }

    public static void updateEntityFromDTO(PontoParadaDTO dto, PontoParada entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PontoParadaDTO dto, PontoParada entity) {
        entity.setNome(dto.getNome());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAtivo(dto.getAtivo());
        entity.setCidade(CidadeMap.fromSummary(dto.getCidade()));
        entity.setVersion(dto.getVersion());
        if (dto.getPontosTuristicosProximos() != null) {
            List<PontoTuristico> pontos = dto.getPontosTuristicosProximos().stream()
                    .map(PontoTuristicoMap::fromSummary)
                    .collect(Collectors.toList());
            entity.setPontosTuristicosProximos(pontos);
        }
    }

    public static PontoParadaDTO toSummary(PontoParada entity) {
        if (entity == null) {
            return null;
        }
        PontoParadaDTO dto = new PontoParadaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    public static PontoParada fromSummary(PontoParadaDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoParada entity = new PontoParada();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        return entity;
    }
}
