package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ManutencaoDTO;
import br.com.bus.application.mapper.ManutencaoMap;
import br.com.bus.domain.Manutencao;
import br.com.bus.repository.ManutencaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarManutencao {

    @Inject
    ManutencaoRepository repository;

    @Transactional
    public ManutencaoDTO executar(Long id, ManutencaoDTO dto) {
        Manutencao entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Manutenção não encontrada: id=" + id));
        ManutencaoMap.updateEntityFromDTO(dto, entity);
        return ManutencaoMap.toDTO(entity);
    }
}
