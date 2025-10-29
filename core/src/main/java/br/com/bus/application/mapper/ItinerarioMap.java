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
            dto.setOrdem(id.getOrdem());
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
        id.setOrdem(dto.getOrdem());

        if (dto.getIdLinha() != null || dto.getNomeLinha() != null) {
            Linha linha = entity.getLinha();
            if (linha == null) {
                linha = new Linha();
            }
            // use all DTO attributes related to Linha
            linha.setId(dto.getIdLinha());
            if (dto.getNomeLinha() != null) {
                linha.setNome(dto.getNomeLinha());
            }
            entity.setLinha(linha);
        } else {
            entity.setLinha(null);
        }

        if (dto.getIdPontoParada() != null || dto.getNomePontoParada() != null) {
            PontoParada pontoParada = entity.getPontoParada();
            if (pontoParada == null) {
                pontoParada = new PontoParada();
            }
            // use all DTO attributes related to PontoParada
            pontoParada.setId(dto.getIdPontoParada());
            if (dto.getNomePontoParada() != null) {
                pontoParada.setNome(dto.getNomePontoParada());
            }
            entity.setPontoParada(pontoParada);
        } else {
            entity.setPontoParada(null);
        }
    }

    public static ItinerarioDTO toSummary(Itinerario entity) {
        if (entity == null) {
            return null;
        }
        ItinerarioDTO dto = new ItinerarioDTO();
        ItinerarioId id = entity.getId();
        if (id != null) {
            dto.setIdLinha(id.getIdLinha());
            dto.setIdPontoParada(id.getIdPontoParada());
            dto.setOrdem(id.getOrdem());
        }
        return dto;
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
