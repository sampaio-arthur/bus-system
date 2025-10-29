package br.com.bus.application.mapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bus.application.dto.PessoaDTO;
import br.com.bus.domain.Pessoa;

public final class PessoaMap {

    private PessoaMap() {
    }

    public static PessoaDTO toDTO(Pessoa entity) {
        if (entity == null) {
            return null;
        }
        PessoaDTO dto = new PessoaDTO();
        copyToDTO(entity, dto);
        return dto;
    }

    public static void updateEntityFromDTO(PessoaDTO dto, Pessoa entity) {
        if (dto == null || entity == null) {
            return;
        }
        copyToEntity(dto, entity);
    }

    public static PessoaDTO toSummary(Pessoa entity) {
        if (entity == null) {
            return null;
        }
        PessoaDTO dto = new PessoaDTO();
        copySummary(entity, dto);
        return dto;
    }

    public static Set<PessoaDTO> toDTOSet(Set<Pessoa> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PessoaMap::toDTO)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Set<PessoaDTO> toSummarySet(Set<Pessoa> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(PessoaMap::toSummary)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Pessoa fromSummary(PessoaDTO dto) {
        if (dto == null) {
            return null;
        }
        Pessoa entity = new Pessoa();
        entity.setId(dto.getId());
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setTipoPessoa(dto.getTipoPessoa());
        return entity;
    }

    private static void copyToDTO(Pessoa entity, PessoaDTO dto) {
        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setAtivo(entity.getAtivo());
        dto.setTipoPessoa(entity.getTipoPessoa());
        dto.setCnh(entity.getCnh());
        dto.setValidadeCnh(entity.getValidadeCnh());
        dto.setCategoriaCnh(entity.getCategoriaCnh());
        dto.setNumeroCarteirinha(entity.getNumeroCarteirinha());
        dto.setVersion(entity.getVersion());
    }

    private static void copyToEntity(PessoaDTO dto, Pessoa entity) {
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setAtivo(dto.getAtivo());
        entity.setTipoPessoa(dto.getTipoPessoa());
        entity.setCnh(dto.getCnh());
        entity.setValidadeCnh(dto.getValidadeCnh());
        entity.setCategoriaCnh(dto.getCategoriaCnh());
        entity.setNumeroCarteirinha(dto.getNumeroCarteirinha());
        entity.setVersion(dto.getVersion());
    }

    private static void copySummary(Pessoa entity, PessoaDTO dto) {
        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
    }
}
