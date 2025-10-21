package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.CidadeDTO;
import br.com.bus.application.mapper.CidadeMap;
import br.com.bus.domain.Cidade;
import br.com.bus.repository.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarCidade {

    @Inject
    CidadeRepository repository;

    @Transactional
    public CidadeDTO executar(CidadeDTO dto) {
        Cidade entity = CidadeMap.toEntity(dto);
        repository.persist(entity);
        return CidadeMap.toDTO(entity);
    }
}

