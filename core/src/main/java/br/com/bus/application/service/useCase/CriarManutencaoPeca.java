package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ManutencaoPecaDTO;
import br.com.bus.application.mapper.ManutencaoPecaMap;
import br.com.bus.domain.manutencaoPeca.ManutencaoPeca;
import br.com.bus.repository.ManutencaoPecaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarManutencaoPeca {

    @Inject
    ManutencaoPecaRepository repository;

    @Transactional
    public ManutencaoPecaDTO executar(ManutencaoPecaDTO dto) {
        ManutencaoPeca entity = ManutencaoPecaMap.toEntity(dto);
        repository.persist(entity);
        return ManutencaoPecaMap.toDTO(entity);
    }
}
