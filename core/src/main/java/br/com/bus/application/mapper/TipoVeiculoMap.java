package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.TipoVeiculoDTO;
import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.domain.TipoVeiculo;
import br.com.bus.domain.Veiculo;

public final class TipoVeiculoMap {

    private TipoVeiculoMap() {
    }

    public static TipoVeiculo toEntity(TipoVeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoVeiculo entity = new TipoVeiculo();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static TipoVeiculoDTO toDTO(TipoVeiculo entity) {
        if (entity == null) {
            return null;
        }
        TipoVeiculoDTO dto = new TipoVeiculoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescrição());
        dto.setAtivo(entity.getAtivo());
        dto.setVersion(entity.getVersion());
        if (entity.getVeiculos() != null) {
            List<VeiculoDTO> veiculos = entity.getVeiculos().stream()
                    .map(VeiculoMap::toSummary)
                    .collect(Collectors.toList());
            dto.setVeiculos(veiculos);
        }
        return dto;
    }

    public static void updateEntityFromDTO(TipoVeiculoDTO dto, TipoVeiculo entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(TipoVeiculoDTO dto, TipoVeiculo entity) {
        entity.setDescrição(dto.getDescricao());
        entity.setAtivo(dto.getAtivo());
        entity.setVersion(dto.getVersion());
        if (dto.getVeiculos() != null) {
            List<Veiculo> veiculos = dto.getVeiculos().stream()
                    .map(VeiculoMap::toEntity)
                    .collect(Collectors.toList());
            entity.setVeiculos(veiculos);
        }
    }

    public static TipoVeiculoDTO toSummary(TipoVeiculo entity) {
        if (entity == null) {
            return null;
        }
        TipoVeiculoDTO dto = new TipoVeiculoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescrição());
        return dto;
    }

    public static TipoVeiculo fromSummary(TipoVeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoVeiculo entity = new TipoVeiculo();
        entity.setId(dto.getId());
        entity.setDescrição(dto.getDescricao());
        return entity;
    }
}
