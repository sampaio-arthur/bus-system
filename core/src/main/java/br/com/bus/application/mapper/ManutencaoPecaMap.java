package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ManutencaoPecaDTO;
import br.com.bus.domain.Manutencao;
import br.com.bus.domain.Peca;
import br.com.bus.domain.manutencaoPeca.ManutencaoPeca;
import br.com.bus.domain.manutencaoPeca.ManutencaoPecaId;

public final class ManutencaoPecaMap {

    private ManutencaoPecaMap() {
    }

    public static ManutencaoPeca toEntity(ManutencaoPecaDTO dto) {
        if (dto == null) {
            return null;
        }
        ManutencaoPeca entity = new ManutencaoPeca();
        entityFromDTO(dto, entity);
        return entity;
    }

    public static ManutencaoPecaDTO toDTO(ManutencaoPeca entity) {
        if (entity == null) {
            return null;
        }
        ManutencaoPecaDTO dto = new ManutencaoPecaDTO();
        if (entity.getId() != null) {
            dto.setIdManutencao(entity.getId().getIdManutencao());
            dto.setIdPeca(entity.getId().getIdPeca());
        }
        dto.setQuantidadeUtilizada(entity.getQuantidadeUtilizada());
        dto.setValorUnitario(entity.getValorUnitario());
        if (entity.getPeca() != null) {
            dto.setNomePeca(entity.getPeca().getNome());
        }
        if (entity.getManutencao() != null) {
            dto.setNomeManutencao(entity.getManutencao().getDescricao());
        }
        return dto;
    }

    public static void updateEntityFromDTO(ManutencaoPecaDTO dto, ManutencaoPeca entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(ManutencaoPecaDTO dto, ManutencaoPeca entity) {
        if (dto == null || entity == null) {
            return;
        }
        ManutencaoPecaId id = entity.getId();
        if (id == null) {
            id = new ManutencaoPecaId();
            entity.setId(id);
        }
        id.setIdManutencao(dto.getIdManutencao());
        id.setIdPeca(dto.getIdPeca());

        if (dto.getIdManutencao() != null) {
            Manutencao manutencao = entity.getManutencao();
            if (manutencao == null) {
                manutencao = new Manutencao();
            }
            manutencao.setId(dto.getIdManutencao());
            entity.setManutencao(manutencao);
        }

        if (dto.getIdPeca() != null) {
            Peca peca = entity.getPeca();
            if (peca == null) {
                peca = new Peca();
            }
            peca.setId(dto.getIdPeca());
            entity.setPeca(peca);
        }

        entity.setQuantidadeUtilizada(dto.getQuantidadeUtilizada());
        entity.setValorUnitario(dto.getValorUnitario());
    }

    public static ManutencaoPecaDTO toSummary(ManutencaoPeca entity) {
        if (entity == null) {
            return null;
        }
        ManutencaoPecaDTO dto = new ManutencaoPecaDTO();
        if (entity.getId() != null) {
            dto.setIdManutencao(entity.getId().getIdManutencao());
            dto.setIdPeca(entity.getId().getIdPeca());
        }
        dto.setQuantidadeUtilizada(entity.getQuantidadeUtilizada());
        dto.setValorUnitario(entity.getValorUnitario());
        return dto;
    }

    public static Set<ManutencaoPecaDTO> toDTOSet(Set<ManutencaoPeca> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ManutencaoPecaMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<ManutencaoPeca> toEntitySet(Set<ManutencaoPecaDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(ManutencaoPecaMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<ManutencaoPecaDTO> toSummarySet(Set<ManutencaoPeca> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ManutencaoPecaMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
