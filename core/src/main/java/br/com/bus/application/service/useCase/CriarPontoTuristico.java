package br.com.bus.application.service.useCase;

import java.util.ArrayList;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.mapper.PontoTuristicoMap;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoTuristico;
import br.com.bus.repository.PontoTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPontoTuristico {

    @Inject
    PontoTuristicoRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public PontoTuristicoDTO executar(PontoTuristicoDTO dto) {
        PontoTuristico entity = PontoTuristicoMap.toEntity(dto);
        entity.setPontosParadaProximos(new ArrayList<>());
        repository.persist(entity);

        if (dto != null && dto.getPontosParadaProximos() != null) {
            dto.getPontosParadaProximos().stream()
                    .filter(p -> p.getId() != null)
                    .map(p -> entityManager.getReference(PontoParada.class, p.getId()))
                    .forEach(pontoParada -> {
                        if (!pontoParada.getPontosTuristicosProximos().contains(entity)) {
                            pontoParada.getPontosTuristicosProximos().add(entity);
                        }
                        if (!entity.getPontosParadaProximos().contains(pontoParada)) {
                            entity.getPontosParadaProximos().add(pontoParada);
                        }
                    });
        }

        return PontoTuristicoMap.toDTO(entity);
    }
}

