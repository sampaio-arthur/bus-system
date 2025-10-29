package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.Viagem;
import br.com.bus.domain.progressoViagem.ProgressoViagem;
import br.com.bus.domain.progressoViagem.ProgressoViagemId;

public final class ProgressoViagemMap {

    private ProgressoViagemMap() {
    }

    public static ProgressoViagem toEntity(ProgressoViagemDTO dto) {
        if (dto == null) {
            return null;
        }
        ProgressoViagem entity = new ProgressoViagem();
        entityFromDTO(dto, entity);
        return entity;
    }

    public static ProgressoViagemDTO toDTO(ProgressoViagem entity) {
        if (entity == null) {
            return null;
        }
        ProgressoViagemDTO dto = new ProgressoViagemDTO();
        ProgressoViagemId id = entity.getId();
        if (id != null) {
            dto.setData(id.getData());
            dto.setIdViagem(id.getIdViagem());
            dto.setIdPontoParada(id.getIdPontoParada());
        }
        return dto;
    }

    public static void updateEntityFromDTO(ProgressoViagemDTO dto, ProgressoViagem entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(ProgressoViagemDTO dto, ProgressoViagem entity) {
        if (dto == null || entity == null) {
            return;
        }
        ProgressoViagemId id = entity.getId();
        if (id == null) {
            id = new ProgressoViagemId();
            entity.setId(id);
        }
        id.setData(dto.getData());
        id.setIdViagem(dto.getIdViagem());
        id.setIdPontoParada(dto.getIdPontoParada());

        if (dto.getIdViagem() != null) {
            Viagem viagem = entity.getViagem();
            if (viagem == null) {
                viagem = new Viagem();
            }
            viagem.setId(dto.getIdViagem());
            entity.setViagem(viagem);
        } else {
            entity.setViagem(null);
        }

        if (dto.getIdPontoParada() != null) {
            PontoParada pontoParada = entity.getPontoParada();
            if (pontoParada == null) {
                pontoParada = new PontoParada();
            }
            pontoParada.setId(dto.getIdPontoParada());
            entity.setPontoParada(pontoParada);
        } else {
            entity.setPontoParada(null);
        }
    }

    public static ProgressoViagemDTO toSummary(ProgressoViagem entity) {
        return toDTO(entity);
    }

    public static Set<ProgressoViagemDTO> toDTOSet(Set<ProgressoViagem> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ProgressoViagemMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<ProgressoViagem> toEntitySet(Set<ProgressoViagemDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(ProgressoViagemMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<ProgressoViagemDTO> toSummarySet(Set<ProgressoViagem> entities) {
        return toDTOSet(entities);
    }
}
