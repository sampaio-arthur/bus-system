package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.RotaDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.domain.Rota;
import br.com.bus.domain.Viagem;

public final class RotaMap {

    private RotaMap() {
    }

    public static Rota toEntity(RotaDTO dto) {
        if (dto == null) {
            return null;
        }
        Rota entity = new Rota();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static RotaDTO toDTO(Rota entity) {
        if (entity == null) {
            return null;
        }
        RotaDTO dto = new RotaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDistanciaKm(entity.getDistanciaKm());
        dto.setTempoEstimado(entity.getTempoEstimado());
        dto.setAtivo(entity.getAtivo());
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        if (entity.getViagens() != null) {
            List<ViagemDTO> viagens = entity.getViagens().stream()
                    .map(ViagemMap::toSummary)
                    .collect(Collectors.toList());
            dto.setViagens(viagens);
        }
        return dto;
    }

    public static void updateEntityFromDTO(RotaDTO dto, Rota entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(RotaDTO dto, Rota entity) {
        entity.setNome(dto.getNome());
        entity.setDistanciaKm(dto.getDistanciaKm());
        entity.setTempoEstimado(dto.getTempoEstimado());
        entity.setAtivo(dto.getAtivo());
        entity.setLinha(LinhaMap.fromSummary(dto.getLinha()));
        if (dto.getViagens() != null) {
            List<Viagem> viagens = dto.getViagens().stream()
                    .map(ViagemMap::toEntity)
                    .collect(Collectors.toList());
            entity.setViagens(viagens);
        }
    }

    public static RotaDTO toSummary(Rota entity) {
        if (entity == null) {
            return null;
        }
        RotaDTO dto = new RotaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    public static Rota fromSummary(RotaDTO dto) {
        if (dto == null) {
            return null;
        }
        Rota entity = new Rota();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        return entity;
    }
}
