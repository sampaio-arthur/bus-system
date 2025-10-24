package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.domain.Passagem;

public final class PassagemMap {

    private PassagemMap() {
    }

    public static Passagem toEntity(PassagemDTO dto) {
        if (dto == null) {
            return null;
        }
        Passagem entity = new Passagem();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PassagemDTO toDTO(Passagem entity) {
        if (entity == null) {
            return null;
        }
        PassagemDTO dto = new PassagemDTO();
        dto.setId(entity.getId());
        dto.setDataCompra(entity.getDataCompra());
        dto.setValor(entity.getValor());
        dto.setDescontoAplicado(entity.getDescontoAplicado());
        dto.setAtivo(entity.getAtivo());
        dto.setPessoa(PessoaMap.toSummary(entity.getPessoa()));
        dto.setTipoPassagem(TipoPassagemMap.toSummary(entity.getTipoPassagem()));
        dto.setMetodoPagamento(MetodoPagamentoMap.toSummary(entity.getMetodoPagamento()));
        dto.setViagem(ViagemMap.toSummary(entity.getViagem()));
        return dto;
    }

    public static void updateEntityFromDTO(PassagemDTO dto, Passagem entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PassagemDTO dto, Passagem entity) {
        entity.setDataCompra(dto.getDataCompra());
        entity.setValor(dto.getValor());
        entity.setDescontoAplicado(dto.getDescontoAplicado());
        entity.setAtivo(dto.getAtivo());
        entity.setPessoa(PessoaMap.fromSummary(dto.getPessoa()));
        entity.setTipoPassagem(TipoPassagemMap.fromSummary(dto.getTipoPassagem()));
        entity.setMetodoPagamento(MetodoPagamentoMap.fromSummary(dto.getMetodoPagamento()));
        entity.setViagem(ViagemMap.fromSummary(dto.getViagem()));
    }

    public static PassagemDTO toSummary(Passagem entity) {
        if (entity == null) {
            return null;
        }
        PassagemDTO dto = new PassagemDTO();
        dto.setId(entity.getId());
        dto.setDataCompra(entity.getDataCompra());
        dto.setValor(entity.getValor());
        dto.setAtivo(entity.getAtivo());
        return dto;
    }

    public static Passagem fromSummary(PassagemDTO dto) {
        if (dto == null) {
            return null;
        }
        Passagem entity = new Passagem();
        entity.setId(dto.getId());
        return entity;
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
