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
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CriarPontoParadaTuristico {

    @Inject
    PontoParadaTuristicoRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public PontoParadaTuristicoDTO executar(PontoParadaTuristicoDTO dto) {
        if (dto == null || dto.getIdPontoParada() == null || dto.getIdPontoTuristico() == null) {
            throw new BadRequestException("Ids de ponto de parada e ponto turístico são obrigatórios");
        }

        PontoParada pontoParada = entityManager.find(PontoParada.class, dto.getIdPontoParada());
        if (pontoParada == null) {
            throw new NotFoundException("Ponto de parada não encontrado: id=" + dto.getIdPontoParada());
        }

        PontoTuristico pontoTuristico = entityManager.find(PontoTuristico.class, dto.getIdPontoTuristico());
        if (pontoTuristico == null) {
            throw new NotFoundException("Ponto turístico não encontrado: id=" + dto.getIdPontoTuristico());
        }

        PontoParadaTuristico entity = new PontoParadaTuristico();
        entity.setPontoParada(pontoParada);
        entity.setPontoTuristico(pontoTuristico);
        entity.setId(new PontoParadaTuristicoId(dto.getIdPontoParada(), dto.getIdPontoTuristico()));

        repository.persist(entity);
        return PontoParadaTuristicoMap.toDTO(entity);
    }
}
