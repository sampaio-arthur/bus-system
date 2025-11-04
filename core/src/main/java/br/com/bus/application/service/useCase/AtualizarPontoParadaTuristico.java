package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoParadaTuristicoDTO;
import br.com.bus.application.mapper.PontoParadaTuristicoMap;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoTuristico;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristico;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristicoId;
import br.com.bus.repository.PontoParadaTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPontoParadaTuristico {

    @Inject
    PontoParadaTuristicoRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public PontoParadaTuristicoDTO executar(Long idPontoParada, Long idPontoTuristico, PontoParadaTuristicoDTO dto) {
        PontoParadaTuristicoId id = new PontoParadaTuristicoId(idPontoParada, idPontoTuristico);
        PontoParadaTuristico entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException(
                        "Ponto parada turístico não encontrado: parada=" + idPontoParada + ", turistico=" + idPontoTuristico));
        aplicarAtualizacoes(entity, id);
        return PontoParadaTuristicoMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(PontoParadaTuristico entity, PontoParadaTuristicoId id) {
        entity.setId(id);
        entity.setPontoParada(entityManager.getReference(PontoParada.class, id.getIdPontoParada()));
        entity.setPontoTuristico(entityManager.getReference(PontoTuristico.class, id.getIdPontoTuristico()));
    }
}
