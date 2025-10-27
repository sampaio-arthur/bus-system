package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ViagemDTO;
import br.com.bus.application.mapper.ViagemMap;
import br.com.bus.domain.Linha;
import br.com.bus.domain.Pessoa;
import br.com.bus.domain.Veiculo;
import br.com.bus.domain.Viagem;
import br.com.bus.repository.ViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class AtualizarViagem {

    @Inject
    ViagemRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public ViagemDTO executar(Long id, ViagemDTO dto) {
        Viagem entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Viagem não encontrada: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return ViagemMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(Viagem entity, ViagemDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setDataPartidaPrevista(dto.getDataPartidaPrevista());
        entity.setDataPartidaReal(dto.getDataPartidaReal());
        entity.setDataChegadaPrevista(dto.getDataChegadaPrevista());
        entity.setDataChegadaReal(dto.getDataChegadaReal());
        entity.setStatus(dto.getStatus());

        if (dto.getLinha() != null && dto.getLinha().getId() != null) {
            entity.setLinha(entityManager.getReference(Linha.class, dto.getLinha().getId()));
        }
        if (dto.getVeiculo() != null && dto.getVeiculo().getId() != null) {
            entity.setVeiculo(entityManager.getReference(Veiculo.class, dto.getVeiculo().getId()));
        }
        if (dto.getMotorista() != null && dto.getMotorista().getId() != null) {
            entity.setMotorista(entityManager.getReference(Pessoa.class, dto.getMotorista().getId()));
        }
        // As passagens possuem ciclo de vida próprio; qualquer alteração deve ocorrer via endpoints específicos.
    }
}

