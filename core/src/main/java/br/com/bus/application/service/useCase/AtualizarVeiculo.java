package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.VeiculoDTO;
import br.com.bus.application.mapper.VeiculoMap;
import br.com.bus.domain.Veiculo;
import br.com.bus.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarVeiculo {

    @Inject
    VeiculoRepository repository;

    @Transactional
    public VeiculoDTO executar(Long id, VeiculoDTO dto) {
        Veiculo entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado: id=" + id));

        VeiculoMap.updateEntityFromDTO(dto, entity);
        return VeiculoMap.toDTO(entity);
    }
}
