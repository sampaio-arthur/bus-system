package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.mapper.PontoTuristicoMap;
import br.com.bus.domain.PontoTuristico;
import br.com.bus.repository.PontoTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPontoTuristico {

    @Inject
    PontoTuristicoRepository repository;

    @Transactional
    public PontoTuristicoDTO executar(Long id, PontoTuristicoDTO dto) {
        PontoTuristico entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("PontoTuristico não encontrado: id=" + id));
        PontoTuristicoMap.updateEntityFromDTO(dto, entity);
        return PontoTuristicoMap.toDTO(entity);
    }
}

