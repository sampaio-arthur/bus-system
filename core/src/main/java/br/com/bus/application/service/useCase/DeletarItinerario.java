package br.com.bus.application.service.useCase;

import br.com.bus.domain.itinerario.ItinerarioId;
import br.com.bus.repository.ItinerarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarItinerario {

    @Inject
    ItinerarioRepository repository;

    @Transactional
    public void executar(Integer idLinha, Integer idPontoParada) {
        ItinerarioId id = new ItinerarioId();
        id.setIdLinha(idLinha);
        id.setIdPontoParada(idPontoParada);
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException(
                    "Itinerário não encontrado: linha=" + idLinha + ", ponto=" + idPontoParada);
        }
    }
}
