package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.FeedbackPassageiroDTO;
import br.com.bus.application.dto.PassageiroDTO;
import br.com.bus.application.dto.PassagemDTO;
import br.com.bus.domain.Passageiro;
import br.com.bus.domain.Passagem;

public final class PassageiroMap {

    private PassageiroMap() {
    }

    public static Passageiro toEntity(PassageiroDTO dto) {
        if (dto == null) {
            return null;
        }
        Passageiro entity = new Passageiro();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static PassageiroDTO toDTO(Passageiro entity) {
        if (entity == null) {
            return null;
        }
        PassageiroDTO dto = new PassageiroDTO();
        PessoaMapperHelper.copyToDTO(entity, dto);
        dto.setNumeroCarteirinha(entity.getNumeroCarteirinha());
        dto.setDescontoEstudante(entity.getDescontoEstudante());
        dto.setDescontoIdoso(entity.getDescontoIdoso());
        if (entity.getPassagens() != null) {
            List<PassagemDTO> passagens = entity.getPassagens().stream()
                    .map(PassagemMap::toSummary)
                    .collect(Collectors.toList());
            dto.setPassagens(passagens);
        }
        if (entity.getFeedbacks() != null) {
            List<FeedbackPassageiroDTO> feedbacks = entity.getFeedbacks().stream()
                    .map(FeedbackPassageiroMap::toSummary)
                    .collect(Collectors.toList());
            dto.setFeedbacks(feedbacks);
        }
        return dto;
    }

    public static void updateEntityFromDTO(PassageiroDTO dto, Passageiro entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(PassageiroDTO dto, Passageiro entity) {
        PessoaMapperHelper.copyToEntity(dto, entity);
        entity.setNumeroCarteirinha(dto.getNumeroCarteirinha());
        entity.setDescontoEstudante(dto.getDescontoEstudante());
        entity.setDescontoIdoso(dto.getDescontoIdoso());
        if (dto.getPassagens() != null) {
            List<Passagem> passagens = dto.getPassagens().stream()
                    .map(PassagemMap::toEntity)
                    .collect(Collectors.toList());
            entity.setPassagens(passagens);
        }
        if (dto.getFeedbacks() != null) {
            entity.setFeedbacks(dto.getFeedbacks().stream()
                    .map(FeedbackPassageiroMap::toEntity)
                    .collect(Collectors.toList()));
        }
    }

    public static PassageiroDTO toSummary(Passageiro entity) {
        if (entity == null) {
            return null;
        }
        PassageiroDTO dto = new PassageiroDTO();
        PessoaMapperHelper.copySummary(entity, dto);
        dto.setNumeroCarteirinha(entity.getNumeroCarteirinha());
        return dto;
    }

    public static Passageiro fromSummary(PassageiroDTO dto) {
        if (dto == null) {
            return null;
        }
        Passageiro entity = new Passageiro();
        entity.setId(dto.getId());
        entity.setNumeroCarteirinha(dto.getNumeroCarteirinha());
        entity.setDescontoEstudante(dto.getDescontoEstudante());
        entity.setDescontoIdoso(dto.getDescontoIdoso());
        return entity;
    }
}
