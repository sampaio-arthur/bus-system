package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.domain.Passagem;
import br.com.bus.domain.Viagem;

public final class ViagemMap {

    private ViagemMap() {
    }

    public static Viagem toEntity(ViagemDTO dto) {
        if (dto == null) {
            return null;
        }
        Viagem entity = new Viagem();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static ViagemDTO toDTO(Viagem entity) {
        if (entity == null) {
            return null;
        }
        ViagemDTO dto = new ViagemDTO();
        dto.setId(entity.getId());
        dto.setDataHoraSaida(entity.getDataHoraSaida());
        dto.setDataHoraChegadaPrevista(entity.getDataHoraChegadaPrevista());
        dto.setDataHoraChegadaReal(entity.getDataHoraChegadaReal());
        dto.setRota(RotaMap.toSummary(entity.getRota()));
        dto.setVeiculo(VeiculoMap.toSummary(entity.getVeiculo()));
        dto.setMotorista(MotoristaMap.toSummary(entity.getMotorista()));
        dto.setStatusViagem(StatusViagemMap.toSummary(entity.getStatusViagem()));
        dto.setVersion(entity.getVersion());
        if (entity.getPassagens() != null) {
            List<PassagemDTO> passagens = entity.getPassagens().stream()
                    .map(PassagemMap::toDTO)
                    .collect(Collectors.toList());
            dto.setPassagens(passagens);
        }
        return dto;
    }

    public static void updateEntityFromDTO(ViagemDTO dto, Viagem entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(ViagemDTO dto, Viagem entity) {
        entity.setDataHoraSaida(dto.getDataHoraSaida());
        entity.setDataHoraChegadaPrevista(dto.getDataHoraChegadaPrevista());
        entity.setDataHoraChegadaReal(dto.getDataHoraChegadaReal());
        entity.setRota(RotaMap.fromSummary(dto.getRota()));
        entity.setVeiculo(VeiculoMap.fromSummary(dto.getVeiculo()));
        entity.setMotorista(MotoristaMap.fromSummary(dto.getMotorista()));
        entity.setStatusViagem(StatusViagemMap.fromSummary(dto.getStatusViagem()));
        entity.setVersion(dto.getVersion());
        if (dto.getPassagens() != null) {
            List<Passagem> passagens = dto.getPassagens().stream()
                    .map(PassagemMap::toEntity)
                    .collect(Collectors.toList());
            entity.setPassagens(passagens);
        }
    }

    public static ViagemDTO toSummary(Viagem entity) {
        if (entity == null) {
            return null;
        }
        ViagemDTO dto = new ViagemDTO();
        dto.setId(entity.getId());
        dto.setDataHoraSaida(entity.getDataHoraSaida());
        dto.setStatusViagem(StatusViagemMap.toSummary(entity.getStatusViagem()));
        return dto;
    }

    public static Viagem fromSummary(ViagemDTO dto) {
        if (dto == null) {
            return null;
        }
        Viagem entity = new Viagem();
        entity.setId(dto.getId());
        entity.setDataHoraSaida(dto.getDataHoraSaida());
        entity.setDataHoraChegadaPrevista(dto.getDataHoraChegadaPrevista());
        entity.setStatusViagem(StatusViagemMap.fromSummary(dto.getStatusViagem()));
        return entity;
    }
}
