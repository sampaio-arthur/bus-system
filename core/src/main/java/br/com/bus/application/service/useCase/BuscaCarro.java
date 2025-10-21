package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.bus.application.dto.CarroDTO;
import br.com.bus.application.mapper.CarroMap;
import br.com.bus.domain.Carro;
import br.com.bus.repository.CarroRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaCarro {

    @Inject
    CarroRepository repository;

    public CarroDTO porId(Long id) {
        Optional<Carro> opt = repository.findByIdOptional(id);
        Carro entity = opt.orElseThrow(() -> new NotFoundException("Carro não encontrado: id=" + id));
        return CarroMap.toDTO(entity);
    }

    public List<CarroDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(CarroMap::toSummary)
                .collect(Collectors.toList());
    }
}
