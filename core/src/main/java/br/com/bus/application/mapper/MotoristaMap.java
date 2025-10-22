package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.MotoristaDTO;
import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.domain.Motorista;
import br.com.bus.domain.Viagem;

public final class MotoristaMap {

    private MotoristaMap() {
    }

    public static Motorista toEntity(MotoristaDTO dto) {
        if (dto == null) {
            return null;
        }
        Motorista entity = new Motorista();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static MotoristaDTO toDTO(Motorista entity) {
        if (entity == null) {
            return null;
        }
        MotoristaDTO dto = new MotoristaDTO();
        PessoaMapperHelper.copyToDTO(entity, dto);
        dto.setCnh(entity.getCnh());
        dto.setDataVencimentoCnh(entity.getDataVencimentoCnh());
        dto.setCategoriaCnh(entity.getCategoriaCnh());
        dto.setAnosExperiencia(entity.getAnosExperiencia());
        dto.setEmpresa(EmpresaTransporteMap.toSummary(entity.getEmpresa()));
        if (entity.getViagens() != null) {
            List<ViagemDTO> viagens = entity.getViagens().stream()
                    .map(ViagemMap::toSummary)
                    .collect(Collectors.toList());
            dto.setViagens(viagens);
        }
        return dto;
    }

    public static void updateEntityFromDTO(MotoristaDTO dto, Motorista entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(MotoristaDTO dto, Motorista entity) {
        PessoaMapperHelper.copyToEntity(dto, entity);
        entity.setCnh(dto.getCnh());
        entity.setDataVencimentoCnh(dto.getDataVencimentoCnh());
        entity.setCategoriaCnh(dto.getCategoriaCnh());
        entity.setAnosExperiencia(dto.getAnosExperiencia());
        entity.setEmpresa(EmpresaTransporteMap.fromSummary(dto.getEmpresa()));
        if (dto.getViagens() != null) {
            List<Viagem> viagens = dto.getViagens().stream()
                    .map(ViagemMap::toEntity)
                    .collect(Collectors.toList());
            entity.setViagens(viagens);
        }
    }

    public static MotoristaDTO toSummary(Motorista entity) {
        if (entity == null) {
            return null;
        }
        MotoristaDTO dto = new MotoristaDTO();
        PessoaMapperHelper.copySummary(entity, dto);
        dto.setCnh(entity.getCnh());
        dto.setCategoriaCnh(entity.getCategoriaCnh());
        return dto;
    }

    public static Motorista fromSummary(MotoristaDTO dto) {
        if (dto == null) {
            return null;
        }
        Motorista entity = new Motorista();
        entity.setId(dto.getId());
        entity.setCnh(dto.getCnh());
        entity.setCategoriaCnh(dto.getCategoriaCnh());
        return entity;
    }
}
