package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.application.mapper.VeiculoMap;
import br.com.bus.domain.Veiculo;
import br.com.bus.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarVeiculo {

    @Inject
    VeiculoRepository repository;

    @Transactional
    public VeiculoDTO executar(VeiculoDTO dto) {
        Veiculo entity = VeiculoMap.toEntity(dto);
        repository.persist(entity);
        return VeiculoMap.toDTO(entity);
    }
}
