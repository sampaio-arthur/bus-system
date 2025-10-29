package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.application.mapper.VeiculoMap;
import br.com.bus.domain.TipoVeiculo;
import br.com.bus.domain.Veiculo;
import br.com.bus.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarVeiculo {

    @Inject
    VeiculoRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public VeiculoDTO executar(Long id, VeiculoDTO dto) {
        Veiculo entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado: id=" + id));

        if (dto == null) {
            return VeiculoMap.toDTO(entity);
        }

        if (dto.getPlaca() != null) {
            entity.setPlaca(dto.getPlaca());
        }
        if (dto.getModelo() != null) {
            entity.setModelo(dto.getModelo());
        }
        if (dto.getAnoFabricacao() != null) {
            entity.setAnoFabricacao(dto.getAnoFabricacao());
        }
        if (dto.getChassi() != null) {
            entity.setChassi(dto.getChassi());
        }
        if (dto.getCapacidade() != null) {
            entity.setCapacidade(dto.getCapacidade());
        }
        if (dto.getAtivo() != null) {
            entity.setAtivo(dto.getAtivo());
        }

        if (dto.getTipoVeiculo() != null && dto.getTipoVeiculo().getId() != null) {
            TipoVeiculo tipoVeiculo = entityManager.find(TipoVeiculo.class, dto.getTipoVeiculo().getId());
            if (tipoVeiculo == null) {
                throw new NotFoundException("Tipo de veículo não encontrado: id=" + dto.getTipoVeiculo().getId());
            }
            entity.setTipoVeiculo(tipoVeiculo);
        }

        return VeiculoMap.toDTO(entity);
    }
}
