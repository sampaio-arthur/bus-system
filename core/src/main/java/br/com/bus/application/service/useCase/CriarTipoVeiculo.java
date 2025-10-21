package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.TipoVeiculoDTO;
import br.com.bus.application.mapper.TipoVeiculoMap;
import br.com.bus.domain.TipoVeiculo;
import br.com.bus.repository.TipoVeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarTipoVeiculo {

    @Inject
    TipoVeiculoRepository repository;

    @Transactional
    public TipoVeiculoDTO executar(TipoVeiculoDTO dto) {
        TipoVeiculo entity = TipoVeiculoMap.toEntity(dto);
        repository.persist(entity);
        return TipoVeiculoMap.toDTO(entity);
    }
}

