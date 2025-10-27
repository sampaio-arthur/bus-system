package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PontoParadaTuristicoDTO;
import br.com.bus.application.mapper.PontoParadaTuristicoMap;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristico;
import br.com.bus.repository.PontoParadaTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPontoParadaTuristico {

    @Inject
    PontoParadaTuristicoRepository repository;

    @Transactional
    public PontoParadaTuristicoDTO executar(Long id, PontoParadaTuristicoDTO dto) {
        PontoParadaTuristico entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Ponto parada turístico não encontrado: id=" + id));
        PontoParadaTuristicoMap.updateEntityFromDTO(dto, entity);
        return PontoParadaTuristicoMap.toDTO(entity);
    }
}
