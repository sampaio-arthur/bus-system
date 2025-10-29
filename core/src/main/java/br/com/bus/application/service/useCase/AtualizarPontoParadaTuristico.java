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
    public PontoParadaTuristicoDTO executar(Long id, PontoParadaTuristicoDTO dto) {
        PontoParadaTuristico entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Ponto parada turístico não encontrado: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return PontoParadaTuristicoMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(PontoParadaTuristico entity, PontoParadaTuristicoDTO dto) {
        if (dto == null) {
            return;
        }
        if (entity.getId() == null) {
            entity.setId(new PontoParadaTuristicoId());
        }
        if (dto.getIdPontoParada() != null) {
            entity.getId().setIdPontoParada(dto.getIdPontoParada());
            entity.setPontoParada(entityManager.getReference(PontoParada.class, dto.getIdPontoParada()));
        }
        if (dto.getIdPontoTuristico() != null) {
            entity.getId().setIdPontoTuristico(dto.getIdPontoTuristico());
            entity.setPontoTuristico(entityManager.getReference(PontoTuristico.class, dto.getIdPontoTuristico()));
        }
    }
}
