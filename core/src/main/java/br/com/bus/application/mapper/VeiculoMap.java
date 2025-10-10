package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.domain.TipoVeiculo;
import br.com.bus.domain.Veiculo;

public final class VeiculoMap {

    private VeiculoMap() {
    }

    public static Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        Veiculo entity = new Veiculo();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static VeiculoDTO toDTO(Veiculo entity) {
        if (entity == null) {
            return null;
        }
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(entity.getId());
        dto.setTipoVeiculo(TipoVeiculoMap.toSummary(entity.getTipoVeiculo()));
        dto.setChassi(entity.getChassi());
        dto.setAnoFabricacao(entity.getAnoFabricacao());
        dto.setCapacidade(entity.getCapacidade());
        dto.setModelo(entity.getModelo());
        dto.setPlaca(entity.getPlaca());
        dto.setManutencoes(ManutencaoMap.toSummarySet(entity.getManutencoes()));
        dto.setViagens(ViagemMap.toSummarySet(entity.getViagens()));
        return dto;
    }

    public static void updateEntityFromDTO(VeiculoDTO dto, Veiculo entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(VeiculoDTO dto, Veiculo entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getTipoVeiculo() != null && dto.getTipoVeiculo().getId() != null) {
            TipoVeiculo tipoVeiculo = entity.getTipoVeiculo();
            if (tipoVeiculo == null) {
                tipoVeiculo = new TipoVeiculo();
            }
            tipoVeiculo.setId(dto.getTipoVeiculo().getId());
            entity.setTipoVeiculo(tipoVeiculo);
        } else {
            entity.setTipoVeiculo(null);
        }
        entity.setChassi(dto.getChassi());
        entity.setAnoFabricacao(dto.getAnoFabricacao());
        entity.setCapacidade(dto.getCapacidade());
        entity.setModelo(dto.getModelo());
        entity.setPlaca(dto.getPlaca());
    }

    public static VeiculoDTO toSummary(Veiculo entity) {
        if (entity == null) {
            return null;
        }
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(entity.getId());
        dto.setModelo(entity.getModelo());
        dto.setPlaca(entity.getPlaca());
        return dto;
    }

    public static Set<VeiculoDTO> toDTOSet(Set<Veiculo> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(VeiculoMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Veiculo> toEntitySet(Set<VeiculoDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(VeiculoMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<VeiculoDTO> toSummarySet(Set<Veiculo> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(VeiculoMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
