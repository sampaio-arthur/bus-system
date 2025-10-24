package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.LinhaDTO;
import br.com.bus.application.dto.ParadaLinhaDTO;
import br.com.bus.domain.Linha;
import br.com.bus.domain.ParadaLinha;

public final class LinhaMap {

    private LinhaMap() {
    }

    public static Linha toEntity(LinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        Linha entity = new Linha();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static LinhaDTO toDTO(Linha entity) {
        if (entity == null) {
            return null;
        }
        LinhaDTO dto = new LinhaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setCor(entity.getCor());
        dto.setAtivo(entity.getAtivo());
        dto.setTempoPercursoEstimado(entity.getTempoPercursoEstimado());
        dto.setVersion(entity.getVersion());
        if (entity.getParadasLinha() != null) {
            List<ParadaLinhaDTO> paradas = entity.getParadasLinha().stream()
                    .map(ParadaLinhaMap::toDTO)
                    .collect(Collectors.toList());
            dto.setParadasLinha(paradas);
        }
        return dto;
    }

    public static void updateEntityFromDTO(LinhaDTO dto, Linha entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(LinhaDTO dto, Linha entity) {
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        entity.setCor(dto.getCor());
        entity.setAtivo(dto.getAtivo());
        entity.setTempoPercursoEstimado(dto.getTempoPercursoEstimado());
        entity.setVersion(dto.getVersion());
        if (dto.getParadasLinha() != null) {
            List<ParadaLinha> paradas = dto.getParadasLinha().stream()
                    .map(ParadaLinhaMap::toEntity)
                    .collect(Collectors.toList());
            entity.setParadasLinha(paradas);
        }
    }

    public static LinhaDTO toSummary(Linha entity) {
        if (entity == null) {
            return null;
        }
        LinhaDTO dto = new LinhaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    public static Linha fromSummary(LinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        Linha entity = new Linha();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        return entity;
    }
}
