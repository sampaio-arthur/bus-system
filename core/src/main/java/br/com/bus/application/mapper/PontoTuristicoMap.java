package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoTuristico;

public final class PontoTuristicoMap {

    private PontoTuristicoMap() {
    }

    public static PontoTuristico toEntity(PontoTuristicoDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoTuristico entity = new PontoTuristico();
        entity.setId(dto.getId());
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
        dto.setCategoria(entity.getCategoria());
        dto.setEndereco(entity.getEndereco());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setAtivo(entity.getAtivo());
        dto.setVersion(entity.getVersion());
        if (entity.getPontosParadaProximos() != null) {
            List<PontoParadaDTO> pontos = entity.getPontosParadaProximos().stream()
                    .map(PontoParadaMap::toSummary)
                    .collect(Collectors.toList());
            dto.setPontosParadaProximos(pontos);
        }
        return dto;
    }

    public static void updateEntityFromDTO(PontoTuristicoDTO dto, PontoTuristico entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PontoTuristicoDTO dto, PontoTuristico entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setCategoria(dto.getCategoria());
        entity.setEndereco(dto.getEndereco());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAtivo(dto.getAtivo());
        entity.setVersion(dto.getVersion());
        if (dto.getPontosParadaProximos() != null) {
            List<PontoParada> pontos = dto.getPontosParadaProximos().stream()
                    .map(PontoParadaMap::fromSummary)
                    .collect(Collectors.toList());
            entity.setPontosParadaProximos(pontos);
        }
    }

    public static PontoTuristicoDTO toSummary(PontoTuristico entity) {
        if (entity == null) {
            return null;
        }
        PontoTuristicoDTO dto = new PontoTuristicoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public static PontoTuristico fromSummary(PontoTuristicoDTO dto) {
        if (dto == null) {
            return null;
        }
        PontoTuristico entity = new PontoTuristico();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }
}
