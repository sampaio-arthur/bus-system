package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PecaDTO;
import br.com.bus.domain.Peca;

public final class PecaMap {

    private PecaMap() {
    }

    public static Peca toEntity(PecaDTO dto) {
        if (dto == null) {
            return null;
        }
        Peca entity = new Peca();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PecaDTO toDTO(Peca entity) {
        if (entity == null) {
            return null;
        }
        PecaDTO dto = new PecaDTO();
        dto.setId(entity.getId());
        dto.setValorUnitario(entity.getValorUnitario());
        dto.setNome(entity.getNome());
        dto.setFabricante(entity.getFabricante());
        dto.setQuantidade(entity.getQuantidade());
        dto.setManutencoes(ManutencaoPecaMap.toDTOSet(entity.getManutencoes()));
        return dto;
    }

    public static void updateEntityFromDTO(PecaDTO dto, Peca entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PecaDTO dto, Peca entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setValorUnitario(dto.getValorUnitario());
        entity.setNome(dto.getNome());
        entity.setFabricante(dto.getFabricante());
        entity.setQuantidade(dto.getQuantidade());
    }

    public static PecaDTO toSummary(Peca entity) {
        if (entity == null) {
            return null;
        }
        PecaDTO dto = new PecaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setFabricante(entity.getFabricante());
        dto.setValorUnitario(entity.getValorUnitario());
        return dto;
    }

    public static Set<PecaDTO> toDTOSet(Set<Peca> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PecaMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Peca> toEntitySet(Set<PecaDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(PecaMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PecaDTO> toSummarySet(Set<Peca> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PecaMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
