package br.com.bus.application.service.useCase;

import br.com.bus.repository.PontoParadaTuristicoRepository;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristicoId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarPontoParadaTuristico {

    @Inject
    PontoParadaTuristicoRepository repository;

    @Transactional
    public void executar(Long idPontoParada, Long idPontoTuristico) {
        PontoParadaTuristicoId id = new PontoParadaTuristicoId(idPontoParada, idPontoTuristico);
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException(
                    "Ponto parada turístico não encontrado: parada=" + idPontoParada + ", turistico=" + idPontoTuristico);
        }
    }
}
