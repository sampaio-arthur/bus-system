package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.CarroDTO;
import br.com.bus.application.mapper.CarroMap;
import br.com.bus.domain.Carro;
import br.com.bus.repository.CarroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarCarro {

    @Inject
    CarroRepository repository;

    @Transactional
    public CarroDTO executar(CarroDTO dto) {
        Carro entity = CarroMap.toEntity(dto);
        repository.persist(entity);
        return CarroMap.toDTO(entity);
    }
}

