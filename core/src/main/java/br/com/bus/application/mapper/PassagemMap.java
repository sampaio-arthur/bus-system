package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.domain.MetodoPagamento;
import br.com.bus.domain.Passagem;
import br.com.bus.domain.Pessoa;
import br.com.bus.domain.TipoPassagem;
import br.com.bus.domain.Viagem;

public final class PassagemMap {

    private PassagemMap() {
    }

    public static Passagem toEntity(PassagemDTO dto) {
        if (dto == null) {
            return null;
        }
        Passagem entity = new Passagem();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PassagemDTO toDTO(Passagem entity) {
        if (entity == null) {
            return null;
        }
        PassagemDTO dto = new PassagemDTO();
        dto.setId(entity.getId());
        dto.setPassageiro(PessoaMap.toSummary(entity.getPassageiro()));
        dto.setViagem(ViagemMap.toSummary(entity.getViagem()));
        dto.setMetodoPagamento(MetodoPagamentoMap.toSummary(entity.getMetodoPagamento()));
        dto.setTipoPassagem(TipoPassagemMap.toSummary(entity.getTipoPassagem()));
        dto.setValor(entity.getValor());
        dto.setDataEmissao(entity.getDataEmissao());
        dto.setNumeroAssento(entity.getNumeroAssento());
        return dto;
    }

    public static void updateEntityFromDTO(PassagemDTO dto, Passagem entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PassagemDTO dto, Passagem entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getPassageiro() != null && dto.getPassageiro().getId() != null) {
            Pessoa passageiro = entity.getPassageiro();
            if (passageiro == null) {
                passageiro = new Pessoa();
            }
            passageiro.setId(dto.getPassageiro().getId());
            entity.setPassageiro(passageiro);
        } else {
            entity.setPassageiro(null);
        }
        if (dto.getViagem() != null && dto.getViagem().getId() != null) {
            Viagem viagem = entity.getViagem();
            if (viagem == null) {
                viagem = new Viagem();
            }
            viagem.setId(dto.getViagem().getId());
            entity.setViagem(viagem);
        } else {
            entity.setViagem(null);
        }
        if (dto.getMetodoPagamento() != null && dto.getMetodoPagamento().getId() != null) {
            MetodoPagamento metodoPagamento = entity.getMetodoPagamento();
            if (metodoPagamento == null) {
                metodoPagamento = new MetodoPagamento();
            }
            metodoPagamento.setId(dto.getMetodoPagamento().getId());
            entity.setMetodoPagamento(metodoPagamento);
        } else {
            entity.setMetodoPagamento(null);
        }
        if (dto.getTipoPassagem() != null && dto.getTipoPassagem().getId() != null) {
            TipoPassagem tipoPassagem = entity.getTipoPassagem();
            if (tipoPassagem == null) {
                tipoPassagem = new TipoPassagem();
            }
            tipoPassagem.setId(dto.getTipoPassagem().getId());
            entity.setTipoPassagem(tipoPassagem);
        } else {
            entity.setTipoPassagem(null);
        }
        entity.setValor(dto.getValor());
        entity.setDataEmissao(dto.getDataEmissao());
        entity.setNumeroAssento(dto.getNumeroAssento());
    }

    public static PassagemDTO toSummary(Passagem entity) {
        if (entity == null) {
            return null;
        }
        PassagemDTO dto = new PassagemDTO();
        dto.setId(entity.getId());
        dto.setValor(entity.getValor());
        dto.setNumeroAssento(entity.getNumeroAssento());
        dto.setDataEmissao(entity.getDataEmissao());
        return dto;
    }

    public static Set<PassagemDTO> toDTOSet(Set<Passagem> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PassagemMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<Passagem> toEntitySet(Set<PassagemDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(PassagemMap::toEntity)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PassagemDTO> toSummarySet(Set<Passagem> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PassagemMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
