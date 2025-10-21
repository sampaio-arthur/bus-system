package br.com.bus.application.service.useCase;

import java.util.Optional;

import br.com.bus.application.dto.CarroDTO;
import br.com.bus.application.mapper.CarroMap;
import br.com.bus.domain.Carro;
import br.com.bus.repository.CarroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarCarro {

    @Inject
    CarroRepository repository;

    @Transactional
    public CarroDTO executar(Long id, CarroDTO dto) {
        Carro entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado: id=" + id));
        CarroMap.updateEntityFromDTO(dto, entity);
        return CarroMap.toDTO(entity);
    }

}
