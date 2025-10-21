package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.HorarioDTO;
import br.com.bus.application.mapper.HorarioMap;
import br.com.bus.repository.HorarioRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaHorario {

    @Inject
    HorarioRepository repository;

    public HorarioDTO porId(Long id) {
        return HorarioMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Horario não encontrado: id=" + id))
        );
    }

    public List<HorarioDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(HorarioMap::toSummary)
                .collect(Collectors.toList());
    }
}

