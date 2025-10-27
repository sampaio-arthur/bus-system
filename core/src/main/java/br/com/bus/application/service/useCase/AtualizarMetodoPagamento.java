package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.MetodoPagamentoDTO;
import br.com.bus.application.mapper.MetodoPagamentoMap;
import br.com.bus.domain.MetodoPagamento;
import br.com.bus.repository.MetodoPagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarMetodoPagamento {

    @Inject
    MetodoPagamentoRepository repository;

    @Transactional
    public MetodoPagamentoDTO executar(Long id, MetodoPagamentoDTO dto) {
        MetodoPagamento entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Método de pagamento não encontrado: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return MetodoPagamentoMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(MetodoPagamento entity, MetodoPagamentoDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setDescricao(dto.getDescricao());
    }
}
