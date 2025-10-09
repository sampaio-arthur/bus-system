package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.EmpresaTransporteDTO;
import br.com.bus.application.dto.MotoristaDTO;
import br.com.bus.domain.EmpresaTransporte;
import br.com.bus.domain.Motorista;

public final class EmpresaTransporteMap {

    private EmpresaTransporteMap() {
    }

    public static EmpresaTransporte toEntity(EmpresaTransporteDTO dto) {
        if (dto == null) {
            return null;
        }
        EmpresaTransporte entity = new EmpresaTransporte();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static EmpresaTransporteDTO toDTO(EmpresaTransporte entity) {
        if (entity == null) {
            return null;
        }
        EmpresaTransporteDTO dto = new EmpresaTransporteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());
        dto.setTelefone(entity.getTelefone());
        dto.setEndereco(entity.getEndereco());
        dto.setAtivo(entity.getAtivo());
        dto.setVersion(entity.getVersion());
        if (entity.getMotoristas() != null) {
            List<MotoristaDTO> motoristas = entity.getMotoristas().stream()
                    .map(MotoristaMap::toSummary)
                    .collect(Collectors.toList());
            dto.setMotoristas(motoristas);
        }
        return dto;
    }

    public static void updateEntityFromDTO(EmpresaTransporteDTO dto, EmpresaTransporte entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(EmpresaTransporteDTO dto, EmpresaTransporte entity) {
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
        entity.setAtivo(dto.getAtivo());
        entity.setVersion(dto.getVersion());
        if (dto.getMotoristas() != null) {
            List<Motorista> motoristas = dto.getMotoristas().stream()
                    .map(MotoristaMap::toEntity)
                    .collect(Collectors.toList());
            entity.setMotoristas(motoristas);
        }
    }

    public static EmpresaTransporteDTO toSummary(EmpresaTransporte entity) {
        if (entity == null) {
            return null;
        }
        EmpresaTransporteDTO dto = new EmpresaTransporteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());
        return dto;
    }

    public static EmpresaTransporte fromSummary(EmpresaTransporteDTO dto) {
        if (dto == null) {
            return null;
        }
        EmpresaTransporte entity = new EmpresaTransporte();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        return entity;
    }
}
