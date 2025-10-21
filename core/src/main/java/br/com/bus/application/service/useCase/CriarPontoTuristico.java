package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.mapper.PontoTuristicoMap;
import br.com.bus.domain.PontoTuristico;
import br.com.bus.repository.PontoTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPontoTuristico {

    @Inject
    PontoTuristicoRepository repository;

    @Transactional
    public PontoTuristicoDTO executar(PontoTuristicoDTO dto) {
        PontoTuristico entity = PontoTuristicoMap.toEntity(dto);
        repository.persist(entity);
        return PontoTuristicoMap.toDTO(entity);
    }
}

