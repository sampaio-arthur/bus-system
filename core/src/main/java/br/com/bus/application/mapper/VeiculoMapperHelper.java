package br.com.bus.application.mapper;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.domain.Veiculo;

final class VeiculoMapperHelper {

    private VeiculoMapperHelper() {
    }

    static void copyToDTO(Veiculo entity, VeiculoDTO dto) {
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setAno(entity.getAno());
        dto.setCapacidade(entity.getCapacidade());
        dto.setAtivo(entity.getAtivo());
        dto.setTipoVeiculo(TipoVeiculoMap.toSummary(entity.getTipoVeiculo()));
        dto.setVersion(entity.getVersion());
    }

    static void copyToEntity(VeiculoDTO dto, Veiculo entity) {
        entity.setPlaca(dto.getPlaca());
        entity.setModelo(dto.getModelo());
        entity.setAno(dto.getAno());
        entity.setCapacidade(dto.getCapacidade());
        entity.setAtivo(dto.getAtivo());
        entity.setTipoVeiculo(TipoVeiculoMap.fromSummary(dto.getTipoVeiculo()));
        entity.setVersion(dto.getVersion());
    }

    static void copySummary(Veiculo entity, VeiculoDTO dto) {
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setAtivo(entity.getAtivo());
        dto.setTipoVeiculo(TipoVeiculoMap.toSummary(entity.getTipoVeiculo()));
    }
}
