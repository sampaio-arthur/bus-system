package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ManutencaoDTO;
import br.com.bus.application.mapper.ManutencaoMap;
import br.com.bus.repository.ManutencaoRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaManutencao {

    @Inject
    ManutencaoRepository repository;

    public ManutencaoDTO porId(Long id) {
        return ManutencaoMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Manutenção não encontrada: id=" + id))
        );
    }

    public List<ManutencaoDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(ManutencaoMap::toDTO)
                .collect(Collectors.toList());
    }
}
