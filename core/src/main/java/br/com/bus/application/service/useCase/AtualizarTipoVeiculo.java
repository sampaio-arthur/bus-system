package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.TipoVeiculoDTO;
import br.com.bus.application.mapper.TipoVeiculoMap;
import br.com.bus.domain.TipoVeiculo;
import br.com.bus.repository.TipoVeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarTipoVeiculo {

    @Inject
    TipoVeiculoRepository repository;

    @Transactional
    public TipoVeiculoDTO executar(Long id, TipoVeiculoDTO dto) {
        TipoVeiculo entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("TipoVeiculo não encontrado: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return TipoVeiculoMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(TipoVeiculo entity, TipoVeiculoDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setDescricao(dto.getDescricao());
        entity.setAtivo(dto.getAtivo());
    }
}
