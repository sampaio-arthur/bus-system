package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.CidadeDTO;
import br.com.bus.application.mapper.CidadeMap;
import br.com.bus.domain.Cidade;
import br.com.bus.repository.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarCidade {

    @Inject
    CidadeRepository repository;

    @Transactional
    public CidadeDTO executar(Long id, CidadeDTO dto) {
        Cidade entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada: id=" + id));
        CidadeMap.updateEntityFromDTO(dto, entity);
        return CidadeMap.toDTO(entity);
    }
}

