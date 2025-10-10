package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.domain.Linha;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.itinerario.Itinerario;
import br.com.bus.domain.itinerario.ItinerarioId;

public final class ItinerarioMap {

    private ItinerarioMap() {
    }

    public static Itinerario toEntity(ItinerarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Itinerario entity = new Itinerario();
        entityFromDTO(dto, entity);
        return entity;
    }

    public static ItinerarioDTO toDTO(Itinerario entity) {
        if (entity == null) {
            return null;
        }
        ItinerarioDTO dto = new ItinerarioDTO();
        ItinerarioId id = entity.getId();
        if (id != null) {
            dto.setIdLinha(id.getIdLinha());
            dto.setIdPontoParada(id.getIdPontoParada());
        }
        if (entity.getLinha() != null) {
            dto.setNomeLinha(entity.getLinha().getNome());
        }
        if (entity.getPontoParada() != null) {
            dto.setNomePontoParada(entity.getPontoParada().getNome());
        }
        return dto;
    }

    public static void updateEntityFromDTO(ItinerarioDTO dto, Itinerario entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(ItinerarioDTO dto, Itinerario entity) {
        if (dto == null || entity == null) {
            return;
        }
        ItinerarioId id = entity.getId();
        if (id == null) {
            id = new ItinerarioId();
            entity.setId(id);
        }
        id.setIdLinha(dto.getIdLinha());
        id.setIdPontoParada(dto.getIdPontoParada());

        if (dto.getIdLinha() != null) {
            Linha linha = entity.getLinha();
            if (linha == null) {
                linha = new Linha();
            }
            linha.setId(dto.getIdLinha());
            entity.setLinha(linha);
        } else {
            entity.setLinha(null);
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

    public static ItinerarioDTO toSummary(Itinerario entity) {
        return toDTO(entity);
    }

    public static Set<ItinerarioDTO> toDTOSet(Set<Itinerario> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ItinerarioMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Itinerario> toEntitySet(Set<ItinerarioDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(ItinerarioMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<ItinerarioDTO> toSummarySet(Set<Itinerario> entities) {
        return toDTOSet(entities);
    }
}
