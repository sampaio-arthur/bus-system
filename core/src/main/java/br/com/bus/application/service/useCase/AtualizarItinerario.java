package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.mapper.ItinerarioMap;
import br.com.bus.domain.itinerario.Itinerario;
import br.com.bus.domain.itinerario.ItinerarioId;
import br.com.bus.repository.ItinerarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarItinerario {

    @Inject
    ItinerarioRepository repository;

    @Transactional
    public ItinerarioDTO executar(Integer idLinha, Integer idPontoParada, ItinerarioDTO dto) {
        ItinerarioId id = new ItinerarioId();
        id.setIdLinha(idLinha);
        id.setIdPontoParada(idPontoParada);
        Itinerario entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException(
                        "Itinerário não encontrado: linha=" + idLinha + ", ponto=" + idPontoParada));
        ItinerarioMap.updateEntityFromDTO(dto, entity);
        return ItinerarioMap.toDTO(entity);
    }
}
