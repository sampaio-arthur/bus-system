package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.MetodoPagamentoDTO;
import br.com.bus.application.mapper.MetodoPagamentoMap;
import br.com.bus.domain.MetodoPagamento;
import br.com.bus.repository.MetodoPagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CriarMetodoPagamento {

    @Inject
    MetodoPagamentoRepository repository;

    @Transactional
    public MetodoPagamentoDTO executar(MetodoPagamentoDTO dto) {
        MetodoPagamento entity = MetodoPagamentoMap.toEntity(dto);
        repository.persist(entity);
        return MetodoPagamentoMap.toDTO(entity);
    }
}
