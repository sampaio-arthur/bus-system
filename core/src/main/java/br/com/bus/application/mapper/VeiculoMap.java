package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.CarroDTO;
import br.com.bus.application.dto.OnibusDTO;
import br.com.bus.application.dto.VanDTO;
import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.domain.Carro;
import br.com.bus.domain.Onibus;
import br.com.bus.domain.Van;
import br.com.bus.domain.Veiculo;
import br.com.bus.domain.Viagem;

public final class VeiculoMap {

    private VeiculoMap() {
    }

    public static Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        if (dto instanceof CarroDTO) {
            return CarroMap.toEntity((CarroDTO) dto);
        }
        if (dto instanceof OnibusDTO) {
            return OnibusMap.toEntity((OnibusDTO) dto);
        }
        if (dto instanceof VanDTO) {
            return VanMap.toEntity((VanDTO) dto);
        }
        throw new IllegalArgumentException("Tipo de DTO de veículo desconhecido: " + dto.getClass());
    }

    public static VeiculoDTO toDTO(Veiculo entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof Carro) {
            return CarroMap.toDTO((Carro) entity);
        }
        if (entity instanceof Onibus) {
            return OnibusMap.toDTO((Onibus) entity);
        }
        if (entity instanceof Van) {
            return VanMap.toDTO((Van) entity);
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
        VeiculoMapperHelper.copyToEntity(dto, entity);
        if (dto.getViagens() != null) {
            List<Viagem> viagens = dto.getViagens().stream()
                    .map(ViagemMap::toEntity)
                    .collect(Collectors.toList());
            entity.setViagens(viagens);
        }
    }

    static void populateDTO(Veiculo entity, VeiculoDTO dto) {
        VeiculoMapperHelper.copyToDTO(entity, dto);
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
        if (entity instanceof Carro) {
            return CarroMap.toSummary((Carro) entity);
        }
        if (entity instanceof Onibus) {
            return OnibusMap.toSummary((Onibus) entity);
        }
        if (entity instanceof Van) {
            return VanMap.toSummary((Van) entity);
        }
        VeiculoDTO dto = new VeiculoDTO();
        VeiculoMapperHelper.copySummary(entity, dto);
        return dto;
    }

    public static Veiculo fromSummary(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }
        if (dto instanceof CarroDTO) {
            return CarroMap.fromSummary((CarroDTO) dto);
        }
        if (dto instanceof OnibusDTO) {
            return OnibusMap.fromSummary((OnibusDTO) dto);
        }
        if (dto instanceof VanDTO) {
            return VanMap.fromSummary((VanDTO) dto);
        }
        throw new IllegalArgumentException("Tipo de DTO de veículo desconhecido: " + dto.getClass());
    }
}
