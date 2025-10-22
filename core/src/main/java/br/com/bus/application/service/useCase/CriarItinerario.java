package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.mapper.ItinerarioMap;
import br.com.bus.domain.itinerario.Itinerario;
import br.com.bus.repository.ItinerarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarItinerario {

    @Inject
    ItinerarioRepository repository;

    @Transactional
    public ItinerarioDTO executar(ItinerarioDTO dto) {
        Itinerario entity = ItinerarioMap.toEntity(dto);
        repository.persist(entity);
        return ItinerarioMap.toDTO(entity);
    }
}
