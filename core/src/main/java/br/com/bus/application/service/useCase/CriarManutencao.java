package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ManutencaoDTO;
import br.com.bus.application.mapper.ManutencaoMap;
import br.com.bus.domain.Manutencao;
import br.com.bus.repository.ManutencaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarManutencao {

    @Inject
    ManutencaoRepository repository;

    @Transactional
    public ManutencaoDTO executar(ManutencaoDTO dto) {
        Manutencao entity = ManutencaoMap.toEntity(dto);
        repository.persist(entity);
        return ManutencaoMap.toDTO(entity);
    }
}
