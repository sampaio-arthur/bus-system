package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.CidadeDTO;
import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.domain.Cidade;
import br.com.bus.domain.PontoParada;

public final class CidadeMap {

    private CidadeMap() {
    }

    public static Cidade toEntity(CidadeDTO dto) {
        if (dto == null) {
            return null;
        }
        Cidade entity = new Cidade();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static CidadeDTO toDTO(Cidade entity) {
        if (entity == null) {
            return null;
        }
        CidadeDTO dto = new CidadeDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEstado(entity.getEstado());
        dto.setCep(entity.getCep());
        dto.setPopulacao(entity.getPopulacao());
        dto.setAtivo(entity.getAtivo());
        dto.setVersion(entity.getVersion());
        if (entity.getPontosParada() != null) {
            List<PontoParadaDTO> pontos = entity.getPontosParada().stream()
                    .map(PontoParadaMap::toSummary)
                    .collect(Collectors.toList());
            dto.setPontosParada(pontos);
        }
        return dto;
    }

    public static void updateEntityFromDTO(CidadeDTO dto, Cidade entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(CidadeDTO dto, Cidade entity) {
        entity.setNome(dto.getNome());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());
        entity.setPopulacao(dto.getPopulacao());
        entity.setAtivo(dto.getAtivo());
        entity.setVersion(dto.getVersion());
        if (dto.getPontosParada() != null) {
            List<PontoParada> pontos = dto.getPontosParada().stream()
                    .map(PontoParadaMap::toEntity)
                    .collect(Collectors.toList());
            entity.setPontosParada(pontos);
        }
    }

    public static CidadeDTO toSummary(Cidade entity) {
        if (entity == null) {
            return null;
        }
        CidadeDTO dto = new CidadeDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    public static Cidade fromSummary(CidadeDTO dto) {
        if (dto == null) {
            return null;
        }
        Cidade entity = new Cidade();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEstado(dto.getEstado());
        return entity;
    }
}
