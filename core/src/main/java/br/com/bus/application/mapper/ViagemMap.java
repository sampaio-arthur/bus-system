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
        dto.setDataPartidaPrevista(dto.getDataPartidaPrevista());
        dto.setDataPartidaReal(entity.getDataPartidaReal());
        dto.setDataChegadaPrevista(entity.getDataChegadaPrevista());
        dto.setDataChegadaReal(entity.getDataChegadaReal());
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        dto.setVeiculo(VeiculoMap.toSummary(entity.getVeiculo()));
        dto.setMotorista(PessoaMap.toSummary(entity.getMotorista()));
        dto.setStatus(entity.getStatus());
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
        entity.setDataPartidaReal(dto.getDataPartidaReal());
        entity.setDataPartidaPrevista(dto.getDataPartidaPrevista());
        entity.setDataChegadaPrevista(dto.getDataChegadaPrevista());
        entity.setDataChegadaReal(dto.getDataChegadaReal());
        entity.setLinha(LinhaMap.fromSummary(dto.getLinha()));
        entity.setVeiculo(VeiculoMap.fromSummary(dto.getVeiculo()));
        entity.setMotorista(PessoaMap.fromSummary(dto.getMotorista()));
        entity.setStatus(dto.getStatus());
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
        dto.setDataPartidaReal(entity.getDataPartidaReal());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static Viagem fromSummary(ViagemDTO dto) {
        if (dto == null) {
            return null;
        }
        Viagem entity = new Viagem();
        entity.setId(dto.getId());
        return entity;
    }
}
