package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.domain.Linha;
import br.com.bus.domain.Pessoa;
import br.com.bus.domain.Veiculo;
import br.com.bus.domain.Viagem;

public final class ViagemMap {

    private ViagemMap() {
    }

    public static Viagem toEntity(ViagemDTO dto) {
        if (dto == null) {
            return null;
        }
        Viagem entity = new Viagem();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static ViagemDTO toDTO(Viagem entity) {
        if (entity == null) {
            return null;
        }
        ViagemDTO dto = new ViagemDTO();
        dto.setId(entity.getId());
        dto.setMotorista(PessoaMap.toSummary(entity.getMotorista()));
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        dto.setVeiculo(VeiculoMap.toSummary(entity.getVeiculo()));
        dto.setDataPartidaPrevista(entity.getDataPartidaPrevista());
        dto.setDataChegadaPrevista(entity.getDataChegadaPrevista());
        dto.setDataPartidaReal(entity.getDataPartidaReal());
        dto.setDataChegadaReal(entity.getDataChegadaReal());
        dto.setStatus(entity.getStatus());
        dto.setProgresso(ProgressoViagemMap.toDTOSet(entity.getProgresso()));
        dto.setPassagens(PassagemMap.toSummarySet(entity.getPassagens()));
        return dto;
    }

    public static void updateEntityFromDTO(ViagemDTO dto, Viagem entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(ViagemDTO dto, Viagem entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getMotorista() != null && dto.getMotorista().getId() != null) {
            Pessoa motorista = entity.getMotorista();
            if (motorista == null) {
                motorista = new Pessoa();
            }
            motorista.setId(dto.getMotorista().getId());
            entity.setMotorista(motorista);
        } else {
            entity.setMotorista(null);
        }
        if (dto.getLinha() != null && dto.getLinha().getId() != null) {
            Linha linha = entity.getLinha();
            if (linha == null) {
                linha = new Linha();
            }
            linha.setId(dto.getLinha().getId());
            entity.setLinha(linha);
        } else {
            entity.setLinha(null);
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
        entity.setDataPartidaPrevista(dto.getDataPartidaPrevista());
        entity.setDataChegadaPrevista(dto.getDataChegadaPrevista());
        entity.setDataPartidaReal(dto.getDataPartidaReal());
        entity.setDataChegadaReal(dto.getDataChegadaReal());
        entity.setStatus(dto.getStatus());
    }

    public static ViagemDTO toSummary(Viagem entity) {
        if (entity == null) {
            return null;
        }
        ViagemDTO dto = new ViagemDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setDataPartidaPrevista(entity.getDataPartidaPrevista());
        dto.setDataChegadaPrevista(entity.getDataChegadaPrevista());
        return dto;
    }

    public static Set<ViagemDTO> toDTOSet(Set<Viagem> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ViagemMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Viagem> toEntitySet(Set<ViagemDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(ViagemMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<ViagemDTO> toSummarySet(Set<Viagem> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(ViagemMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
