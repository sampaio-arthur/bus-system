package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ManutencaoDTO;
import br.com.bus.domain.Manutencao;
import br.com.bus.domain.Pessoa;
import br.com.bus.domain.Veiculo;

public final class ManutencaoMap {

    private ManutencaoMap() {
    }

    public static Manutencao toEntity(ManutencaoDTO dto) {
        if (dto == null) {
            return null;
        }
        Manutencao entity = new Manutencao();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static ManutencaoDTO toDTO(Manutencao entity) {
        if (entity == null) {
            return null;
        }
        ManutencaoDTO dto = new ManutencaoDTO();
        dto.setId(entity.getId());
        dto.setVeiculo(VeiculoMap.toSummary(entity.getVeiculo()));
        dto.setMecanico(PessoaMap.toSummary(entity.getMecanico()));
        dto.setDescricao(entity.getDescricao());
        dto.setCustoTotal(entity.getCustoTotal());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());
        dto.setManutencaoPecas(ManutencaoPecaMap.toDTOSet(entity.getManutencaoPecas()));
        return dto;
    }

    public static void updateEntityFromDTO(ManutencaoDTO dto, Manutencao entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(ManutencaoDTO dto, Manutencao entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getVeiculo() != null && dto.getVeiculo().getId() != null) {
            Veiculo veiculo = entity.getVeiculo();
            if (veiculo == null) {
                veiculo = new Veiculo();
            }
            veiculo.setId(dto.getVeiculo().getId());
            entity.setVeiculo(veiculo);
        } else {
            entity.setVeiculo(null);
        }
        if (dto.getMecanico() != null && dto.getMecanico().getId() != null) {
            Pessoa mecanico = entity.getMecanico();
            if (mecanico == null) {
                mecanico = new Pessoa();
            }
            mecanico.setId(dto.getMecanico().getId());
            entity.setMecanico(mecanico);
        } else {
            entity.setMecanico(null);
        }
        entity.setDescricao(dto.getDescricao());
        entity.setCustoTotal(dto.getCustoTotal());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());
    }

    public static ManutencaoDTO toSummary(Manutencao entity) {
        if (entity == null) {
            return null;
        }
        ManutencaoDTO dto = new ManutencaoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());
        return dto;
    }

    public static Set<ManutencaoDTO> toDTOSet(Set<Manutencao> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ManutencaoMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Manutencao> toEntitySet(Set<ManutencaoDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(ManutencaoMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<ManutencaoDTO> toSummarySet(Set<Manutencao> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ManutencaoMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
