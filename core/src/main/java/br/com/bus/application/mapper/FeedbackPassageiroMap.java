package br.com.bus.application.mapper;

import br.com.bus.application.dto.FeedbackPassageiroDTO;
import br.com.bus.domain.FeedbackPassageiro;

public final class FeedbackPassageiroMap {

    private FeedbackPassageiroMap() {
    }

    public static FeedbackPassageiro toEntity(FeedbackPassageiroDTO dto) {
        if (dto == null) {
            return null;
        }
        FeedbackPassageiro entity = new FeedbackPassageiro();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static FeedbackPassageiroDTO toDTO(FeedbackPassageiro entity) {
        if (entity == null) {
            return null;
        }
        FeedbackPassageiroDTO dto = new FeedbackPassageiroDTO();
        dto.setId(entity.getId());
        dto.setNota(entity.getNota());
        dto.setComentario(entity.getComentario());
        dto.setDataFeedback(entity.getDataFeedback());
        dto.setPassageiro(PassageiroMap.toSummary(entity.getPassageiro()));
        dto.setLinha(LinhaMap.toSummary(entity.getLinha()));
        return dto;
    }

    public static void updateEntityFromDTO(FeedbackPassageiroDTO dto, FeedbackPassageiro entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(FeedbackPassageiroDTO dto, FeedbackPassageiro entity) {
        entity.setNota(dto.getNota());
        entity.setComentario(dto.getComentario());
        entity.setDataFeedback(dto.getDataFeedback());
        entity.setPassageiro(PassageiroMap.fromSummary(dto.getPassageiro()));
        entity.setLinha(LinhaMap.fromSummary(dto.getLinha()));
    }

    public static FeedbackPassageiroDTO toSummary(FeedbackPassageiro entity) {
        if (entity == null) {
            return null;
        }
        FeedbackPassageiroDTO dto = new FeedbackPassageiroDTO();
        dto.setId(entity.getId());
        dto.setNota(entity.getNota());
        dto.setComentario(entity.getComentario());
        return dto;
    }

    public static FeedbackPassageiro fromSummary(FeedbackPassageiroDTO dto) {
        if (dto == null) {
            return null;
        }
        FeedbackPassageiro entity = new FeedbackPassageiro();
        entity.setId(dto.getId());
        entity.setNota(dto.getNota());
        entity.setComentario(dto.getComentario());
        return entity;
    }
}
