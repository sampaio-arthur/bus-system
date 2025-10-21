package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PassageiroDTO;
import br.com.bus.application.mapper.PassageiroMap;
import br.com.bus.domain.Passageiro;
import br.com.bus.repository.PassageiroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPassageiro {

    @Inject
    PassageiroRepository repository;

    @Transactional
    public PassageiroDTO executar(Long id, PassageiroDTO dto) {
        Passageiro entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Passageiro não encontrado: id=" + id));
        PassageiroMap.updateEntityFromDTO(dto, entity);
        return PassageiroMap.toDTO(entity);
    }
}

