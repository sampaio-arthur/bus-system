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

    public static Pessoa toEntity(PessoaDTO dto) {
        if (dto == null) {
            return null;
        }
        Pessoa entity = new Pessoa();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PessoaDTO toDTO(Pessoa entity) {
        if (entity == null) {
            return null;
        }
        PessoaDTO dto = new PessoaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setCnh(entity.getCnh());
        dto.setValidadeCnh(entity.getValidadeCnh());
        dto.setCategoriaCnh(entity.getCategoriaCnh());
        dto.setEspecialidade(entity.getEspecialidade());
        dto.setViagens(ViagemMap.toSummarySet(entity.getViagens()));
        dto.setPassagens(PassagemMap.toSummarySet(entity.getPassagens()));
        dto.setManutencoes(ManutencaoMap.toSummarySet(entity.getManutencoes()));
        return dto;
    }

    public static void updateEntityFromDTO(PessoaDTO dto, Pessoa entity) {
        if (entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PessoaDTO dto, Pessoa entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        entity.setCnh(dto.getCnh());
        entity.setValidadeCnh(dto.getValidadeCnh());
        entity.setCategoriaCnh(dto.getCategoriaCnh());
        entity.setEspecialidade(dto.getEspecialidade());
    }

    public static PessoaDTO toSummary(Pessoa entity) {
        if (entity == null) {
            return null;
        }
        PessoaDTO dto = new PessoaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
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

    public static Set<Pessoa> toEntitySet(Set<PessoaDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream()
                .map(PessoaMap::toEntity)
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
}
