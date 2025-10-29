package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.application.mapper.PassagemMap;
import br.com.bus.domain.MetodoPagamento;
import br.com.bus.domain.Passagem;
import br.com.bus.domain.Pessoa;
import br.com.bus.domain.TipoPassagem;
import br.com.bus.domain.Viagem;
import br.com.bus.repository.PassagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class AtualizarPassagem {

    @Inject
    PassagemRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public PassagemDTO executar(Long id, PassagemDTO dto) {
        Passagem entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Passagem não encontrada: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return PassagemMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(Passagem entity, PassagemDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setDataEmissao(dto.getDataEmissao());
        entity.setNumeroAssento(dto.getNumeroAssento());
        entity.setValor(dto.getValor());
        entity.setDescontoAplicado(dto.getDescontoAplicado());
        entity.setAtivo(dto.getAtivo());

        if (dto.getPessoa() != null && dto.getPessoa().getId() != null) {
            entity.setPessoa(resolverReferencia(Pessoa.class, dto.getPessoa().getId()));
        }
        if (dto.getTipoPassagem() != null && dto.getTipoPassagem().getId() != null) {
            entity.setTipoPassagem(resolverReferencia(TipoPassagem.class, dto.getTipoPassagem().getId()));
        }
        if (dto.getMetodoPagamento() != null && dto.getMetodoPagamento().getId() != null) {
            entity.setMetodoPagamento(resolverReferencia(MetodoPagamento.class, dto.getMetodoPagamento().getId()));
        }
        if (dto.getViagem() != null && dto.getViagem().getId() != null) {
            entity.setViagem(resolverReferencia(Viagem.class, dto.getViagem().getId()));
        }
    }

    private <T> T resolverReferencia(Class<T> tipo, Long id) {
        if (id == null) {
            return null;
        }
        return entityManager.getReference(tipo, id);
    }
}

