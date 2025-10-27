package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.domain.Veiculo;
import br.com.bus.domain.Viagem;

public final class VeiculoMap {

    private VeiculoMap() {
    }

    public static Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        Veiculo entity = new Veiculo();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static VeiculoDTO toDTO(Veiculo entity) {
        if (entity == null) {
            return null;
        }
        VeiculoDTO dto = new VeiculoDTO();
        populateDTO(entity, dto);
        return dto;
    }

    public static void updateEntityFromDTO(VeiculoDTO dto, Veiculo entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    static void entityFromDTO(VeiculoDTO dto, Veiculo entity) {
        copyToEntity(dto, entity);
        if (dto.getViagens() != null) {
            List<Viagem> viagens = dto.getViagens().stream()
                    .map(ViagemMap::toEntity)
                    .collect(Collectors.toList());
            entity.setViagens(viagens);
        }
    }

    static void populateDTO(Veiculo entity, VeiculoDTO dto) {
        copyToDTO(entity, dto);
        if (entity.getViagens() != null) {
            List<ViagemDTO> viagens = entity.getViagens().stream()
                    .map(ViagemMap::toSummary)
                    .collect(Collectors.toList());
            dto.setViagens(viagens);
        }
    }

    public static VeiculoDTO toSummary(Veiculo entity) {
        if (entity == null) {
            return null;
        }
        VeiculoDTO dto = new VeiculoDTO();
        copySummary(entity, dto);
        return dto;
    }

    public static Veiculo fromSummary(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        Veiculo entity = new Veiculo();
        entity.setId(dto.getId());
        entity.setPlaca(dto.getPlaca());
        entity.setModelo(dto.getModelo());
        entity.setAtivo(dto.getAtivo());
        entity.setTipoVeiculo(TipoVeiculoMap.fromSummary(dto.getTipoVeiculo()));
        return entity;
    }

    private static void copyToDTO(Veiculo entity, VeiculoDTO dto) {
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setAnoFabricacao(entity.getAnoFabricacao());
        dto.setCapacidade(entity.getCapacidade());
        dto.setChassi(entity.getChassi());
        dto.setAtivo(entity.getAtivo());
        dto.setTipoVeiculo(TipoVeiculoMap.toSummary(entity.getTipoVeiculo()));
        dto.setVersion(entity.getVersion());
    }

    private static void copyToEntity(VeiculoDTO dto, Veiculo entity) {
        entity.setPlaca(dto.getPlaca());
        entity.setModelo(dto.getModelo());
        entity.setAnoFabricacao(dto.getAnoFabricacao());
        entity.setChassi(dto.getChassi());
        entity.setCapacidade(dto.getCapacidade());
        entity.setAtivo(dto.getAtivo());
        entity.setTipoVeiculo(TipoVeiculoMap.fromSummary(dto.getTipoVeiculo()));
        entity.setVersion(dto.getVersion());
    }

    private static void copySummary(Veiculo entity, VeiculoDTO dto) {
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setAtivo(entity.getAtivo());
        dto.setTipoVeiculo(TipoVeiculoMap.toSummary(entity.getTipoVeiculo()));
    }
}
