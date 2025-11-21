package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.mapper.ItinerarioMap;
import br.com.bus.domain.itinerario.ItinerarioId;
import br.com.bus.repository.ItinerarioRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaItinerario {

    @Inject
    ItinerarioRepository repository;

    public ItinerarioDTO porId(Short ordem, Long idLinha, Long idPontoParada) {
        ItinerarioId id = new ItinerarioId();
        id.setOrdem(ordem);
        id.setIdLinha(idLinha);
        id.setIdPontoParada(idPontoParada);
        return ItinerarioMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Itinerário não encontrado: ordem=" + ordem + ", linha=" + idLinha + ", ponto=" + idPontoParada))
        );
    }

    public List<ItinerarioDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(ItinerarioMap::toDTO)
                .collect(Collectors.toList());
    }
}
