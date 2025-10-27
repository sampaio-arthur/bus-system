package br.com.bus.application.service.useCase;

import java.util.List;

import br.com.bus.application.dto.PontoParadaDTO;
import br.com.bus.application.mapper.PontoParadaMap;
import br.com.bus.domain.Cidade;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoTuristico;
import br.com.bus.repository.PontoParadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class AtualizarPontoParada {

    @Inject
    PontoParadaRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public PontoParadaDTO executar(Long id, PontoParadaDTO dto) {
        PontoParada entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("PontoParada não encontrado: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return PontoParadaMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(PontoParada entity, PontoParadaDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setNome(dto.getNome());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAtivo(dto.getAtivo());

        if (dto.getCidade() != null && dto.getCidade().getId() != null) {
            entity.setCidade(entityManager.getReference(Cidade.class, dto.getCidade().getId()));
        }

        if (dto.getPontosTuristicosProximos() != null) {
            List<PontoTuristico> proximos = entity.getPontosTuristicosProximos();
            proximos.clear();
            dto.getPontosTuristicosProximos().stream()
                    .filter(p -> p.getId() != null)
                    .map(p -> entityManager.getReference(PontoTuristico.class, p.getId()))
                    .forEach(proximos::add);
        }
    }
}

