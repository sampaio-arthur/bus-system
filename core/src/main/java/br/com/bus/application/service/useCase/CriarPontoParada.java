package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.mapper.PontoParadaMap;
import br.com.bus.domain.Cidade;
import br.com.bus.domain.PontoParada;
import br.com.bus.repository.PontoParadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CriarPontoParada {

    @Inject
    PontoParadaRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public PontoParadaDTO executar(PontoParadaDTO dto) {
        PontoParada entity = PontoParadaMap.toEntity(dto);
        if (dto != null && dto.getCidade() != null && dto.getCidade().getId() != null) {
            entity.setCidade(entityManager.getReference(Cidade.class, dto.getCidade().getId()));
        }
        repository.persist(entity);
        return PontoParadaMap.toDTO(entity);
    }
}

