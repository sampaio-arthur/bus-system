package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.mapper.PontoParadaMap;
import br.com.bus.domain.PontoParada;
import br.com.bus.repository.PontoParadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPontoParada {

    @Inject
    PontoParadaRepository repository;

    @Transactional
    public PontoParadaDTO executar(Long id, PontoParadaDTO dto) {
        PontoParada entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("PontoParada não encontrado: id=" + id));
        PontoParadaMap.updateEntityFromDTO(dto, entity);
        return PontoParadaMap.toDTO(entity);
    }
}

