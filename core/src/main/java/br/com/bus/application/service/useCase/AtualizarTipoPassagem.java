package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.TipoPassagemDTO;
import br.com.bus.application.mapper.TipoPassagemMap;
import br.com.bus.domain.TipoPassagem;
import br.com.bus.repository.TipoPassagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarTipoPassagem {

    @Inject
    TipoPassagemRepository repository;

    @Transactional
    public TipoPassagemDTO executar(Long id, TipoPassagemDTO dto) {
        TipoPassagem entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Tipo de passagem não encontrado: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return TipoPassagemMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(TipoPassagem entity, TipoPassagemDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setDescricao(dto.getDescricao());
    }
}
