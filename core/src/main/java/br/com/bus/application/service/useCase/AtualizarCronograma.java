package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.CronogramaDTO;
import br.com.bus.application.mapper.CronogramaMap;
import br.com.bus.domain.Cronograma;
import br.com.bus.domain.Linha;
import br.com.bus.repository.CronogramaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarCronograma {

    @Inject
    CronogramaRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public CronogramaDTO executar(Long id, CronogramaDTO dto) {
        Cronograma entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cronograma não encontrado: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return CronogramaMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(Cronograma entity, CronogramaDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setHoraPartida(dto.getHoraPartida());
        entity.setTipoDia(dto.getTipoDia());

        if (dto.getLinha() != null && dto.getLinha().getId() != null) {
            entity.setLinha(entityManager.getReference(Linha.class, dto.getLinha().getId()));
        }
    }
}
