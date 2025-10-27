package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.mapper.ItinerarioMap;
import br.com.bus.domain.Linha;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.itinerario.Itinerario;
import br.com.bus.domain.itinerario.ItinerarioId;
import br.com.bus.repository.ItinerarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarItinerario {

    @Inject
    ItinerarioRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public ItinerarioDTO executar(Long idLinha, Long idPontoParada, ItinerarioDTO dto) {
        ItinerarioId id = new ItinerarioId();
        id.setIdLinha(idLinha);
        id.setIdPontoParada(idPontoParada);
        Itinerario entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException(
                        "Itinerário não encontrado: linha=" + idLinha + ", ponto=" + idPontoParada));
        aplicarAtualizacoes(entity, id);
        return ItinerarioMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(Itinerario entity, ItinerarioId id) {
        entity.setId(id);
        entity.setLinha(entityManager.getReference(Linha.class, id.getIdLinha()));
        entity.setPontoParada(entityManager.getReference(PontoParada.class, id.getIdPontoParada()));
    }
}
