package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.StatusViagemDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.domain.StatusViagem;
import br.com.bus.domain.Viagem;

public final class StatusViagemMap {

    private StatusViagemMap() {
    }

    public static StatusViagem toEntity(StatusViagemDTO dto) {
        if (dto == null) {
            return null;
        }
        StatusViagem entity = new StatusViagem();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static StatusViagemDTO toDTO(StatusViagem entity) {
        if (entity == null) {
            return null;
        }
        StatusViagemDTO dto = new StatusViagemDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setAtivo(entity.getAtivo());
        dto.setVersion(entity.getVersion());
        if (entity.getViagens() != null) {
            List<ViagemDTO> viagens = entity.getViagens().stream()
                    .map(ViagemMap::toSummary)
                    .collect(Collectors.toList());
            dto.setViagens(viagens);
        }
        return dto;
    }

    public static void updateEntityFromDTO(StatusViagemDTO dto, StatusViagem entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(StatusViagemDTO dto, StatusViagem entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setAtivo(dto.getAtivo());
        entity.setVersion(dto.getVersion());
        if (dto.getViagens() != null) {
            List<Viagem> viagens = dto.getViagens().stream()
                    .map(ViagemMap::toEntity)
                    .collect(Collectors.toList());
            entity.setViagens(viagens);
        }
    }

    public static StatusViagemDTO toSummary(StatusViagem entity) {
        if (entity == null) {
            return null;
        }
        StatusViagemDTO dto = new StatusViagemDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    public static StatusViagem fromSummary(StatusViagemDTO dto) {
        if (dto == null) {
            return null;
        }
        StatusViagem entity = new StatusViagem();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        return entity;
    }
}
