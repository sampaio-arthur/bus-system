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
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CriarVeiculo {

    @Inject
    VeiculoRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public VeiculoDTO executar(VeiculoDTO dto) {
        if (dto == null || dto.getTipoVeiculo() == null || dto.getTipoVeiculo().getId() == null) {
            throw new BadRequestException("Tipo de veículo é obrigatório");
        }

        TipoVeiculo tipoVeiculo = entityManager.find(TipoVeiculo.class, dto.getTipoVeiculo().getId());
        if (tipoVeiculo == null) {
            throw new NotFoundException("Tipo de veículo não encontrado: id=" + dto.getTipoVeiculo().getId());
        }

        Veiculo entity = VeiculoMap.toEntity(dto);
        entity.setTipoVeiculo(tipoVeiculo);

        repository.persist(entity);
        return VeiculoMap.toDTO(entity);
    }
}
