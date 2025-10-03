package br.com.bus.application.mapper;

import br.com.bus.application.dto.PessoaDTO;
import br.com.bus.domain.Pessoa;

final class PessoaMapperHelper {

    private PessoaMapperHelper() {
    }

    static void copyToDTO(Pessoa entity, PessoaDTO dto) {
        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setEndereco(entity.getEndereco());
        dto.setAtivo(entity.getAtivo());
        dto.setVersion(entity.getVersion());
    }

    static void copyToEntity(PessoaDTO dto, Pessoa entity) {
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setEndereco(dto.getEndereco());
        entity.setAtivo(dto.getAtivo());
        entity.setVersion(dto.getVersion());
    }

    static void copySummary(Pessoa entity, PessoaDTO dto) {
        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
    }
}
