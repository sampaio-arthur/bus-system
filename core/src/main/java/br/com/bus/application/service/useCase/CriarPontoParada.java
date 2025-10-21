package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.mapper.PontoParadaMap;
import br.com.bus.domain.PontoParada;
import br.com.bus.repository.PontoParadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPontoParada {

    @Inject
    PontoParadaRepository repository;

    @Transactional
    public PontoParadaDTO executar(PontoParadaDTO dto) {
        PontoParada entity = PontoParadaMap.toEntity(dto);
        repository.persist(entity);
        return PontoParadaMap.toDTO(entity);
    }
}

