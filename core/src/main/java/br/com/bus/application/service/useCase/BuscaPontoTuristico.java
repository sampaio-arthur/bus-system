package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.mapper.PontoTuristicoMap;
import br.com.bus.repository.PontoTuristicoRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaPontoTuristico {

    @Inject
    PontoTuristicoRepository repository;

    public PontoTuristicoDTO porId(Long id) {
        return PontoTuristicoMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("PontoTuristico não encontrado: id=" + id))
        );
    }

    public List<PontoTuristicoDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(PontoTuristicoMap::toDTO)
                .collect(Collectors.toList());
    }
}

