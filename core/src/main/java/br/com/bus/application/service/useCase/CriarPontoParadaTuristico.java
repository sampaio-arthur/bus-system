package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoParadaTuristicoDTO;
import br.com.bus.application.mapper.PontoParadaTuristicoMap;
import br.com.bus.domain.PontoParadaTuristico;
import br.com.bus.repository.PontoParadaTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarPontoParadaTuristico {

    @Inject
    PontoParadaTuristicoRepository repository;

    @Transactional
    public PontoParadaTuristicoDTO executar(PontoParadaTuristicoDTO dto) {
        PontoParadaTuristico entity = PontoParadaTuristicoMap.toEntity(dto);
        repository.persist(entity);
        return PontoParadaTuristicoMap.toDTO(entity);
    }
}
