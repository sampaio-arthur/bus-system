package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ManutencaoDTO;
import br.com.bus.application.mapper.ManutencaoMap;
import br.com.bus.domain.Manutencao;
import br.com.bus.domain.Pessoa;
import br.com.bus.domain.Veiculo;
import br.com.bus.repository.ManutencaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarManutencao {

    @Inject
    ManutencaoRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public ManutencaoDTO executar(Long id, ManutencaoDTO dto) {
        Manutencao entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Manutenção não encontrada: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return ManutencaoMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(Manutencao entity, ManutencaoDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setDescricao(dto.getDescricao());
        entity.setCustoTotal(dto.getCustoTotal());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());

        if (dto.getVeiculo() != null && dto.getVeiculo().getId() != null) {
            entity.setVeiculo(entityManager.getReference(Veiculo.class, dto.getVeiculo().getId()));
        }
        if (dto.getMecanico() != null && dto.getMecanico().getId() != null) {
            entity.setMecanico(entityManager.getReference(Pessoa.class, dto.getMecanico().getId()));
        }
        // ManutencaoPeca possui endpoint dedicado, evitando cascatas indevidas aqui.
    }
}
