package br.com.bus.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.FeedbackPassageiroDTO;
import br.com.bus.application.dto.HorarioDTO;
import br.com.bus.application.dto.LinhaDTO;
import br.com.bus.application.dto.ParadaLinhaDTO;
import br.com.bus.application.dto.RotaDTO;
import br.com.bus.application.dto.TarifaDTO;
import br.com.bus.domain.FeedbackPassageiro;
import br.com.bus.domain.Horario;
import br.com.bus.domain.Linha;
import br.com.bus.domain.ParadaLinha;
import br.com.bus.domain.Rota;
import br.com.bus.domain.Tarifa;

public final class LinhaMap {

    private LinhaMap() {
    }

    public static Linha toEntity(LinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        Linha entity = new Linha();
        entity.setId(dto.getId());
        entityFromDTO(dto, entity);
        return entity;
    }

    public static LinhaDTO toDTO(Linha entity) {
        if (entity == null) {
            return null;
        }
        LinhaDTO dto = new LinhaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setCor(entity.getCor());
        dto.setAtivo(entity.getAtivo());
        dto.setTempoPercursoEstimado(entity.getTempoPercursoEstimado());
        dto.setVersion(entity.getVersion());
        if (entity.getRotas() != null) {
            List<RotaDTO> rotas = entity.getRotas().stream()
                    .map(RotaMap::toDTO)
                    .collect(Collectors.toList());
            dto.setRotas(rotas);
        }
        if (entity.getParadasLinha() != null) {
            List<ParadaLinhaDTO> paradas = entity.getParadasLinha().stream()
                    .map(ParadaLinhaMap::toDTO)
                    .collect(Collectors.toList());
            dto.setParadasLinha(paradas);
        }
        if (entity.getHorarios() != null) {
            List<HorarioDTO> horarios = entity.getHorarios().stream()
                    .map(HorarioMap::toDTO)
                    .collect(Collectors.toList());
            dto.setHorarios(horarios);
        }
        if (entity.getTarifas() != null) {
            List<TarifaDTO> tarifas = entity.getTarifas().stream()
                    .map(TarifaMap::toDTO)
                    .collect(Collectors.toList());
            dto.setTarifas(tarifas);
        }
        if (entity.getFeedbacks() != null) {
            List<FeedbackPassageiroDTO> feedbacks = entity.getFeedbacks().stream()
                    .map(FeedbackPassageiroMap::toDTO)
                    .collect(Collectors.toList());
            dto.setFeedbacks(feedbacks);
        }
        return dto;
    }

    public static void updateEntityFromDTO(LinhaDTO dto, Linha entity) {
        if (dto == null || entity == null) {
            return;
        }
        entityFromDTO(dto, entity);
    }

    private static void entityFromDTO(LinhaDTO dto, Linha entity) {
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        entity.setCor(dto.getCor());
        entity.setAtivo(dto.getAtivo());
        entity.setTempoPercursoEstimado(dto.getTempoPercursoEstimado());
        entity.setVersion(dto.getVersion());
        if (dto.getRotas() != null) {
            List<Rota> rotas = dto.getRotas().stream()
                    .map(RotaMap::toEntity)
                    .collect(Collectors.toList());
            entity.setRotas(rotas);
        }
        if (dto.getParadasLinha() != null) {
            List<ParadaLinha> paradas = dto.getParadasLinha().stream()
                    .map(ParadaLinhaMap::toEntity)
                    .collect(Collectors.toList());
            entity.setParadasLinha(paradas);
        }
        if (dto.getHorarios() != null) {
            List<Horario> horarios = dto.getHorarios().stream()
                    .map(HorarioMap::toEntity)
                    .collect(Collectors.toList());
            entity.setHorarios(horarios);
        }
        if (dto.getTarifas() != null) {
            List<Tarifa> tarifas = dto.getTarifas().stream()
                    .map(TarifaMap::toEntity)
                    .collect(Collectors.toList());
            entity.setTarifas(tarifas);
        }
        if (dto.getFeedbacks() != null) {
            List<FeedbackPassageiro> feedbacks = dto.getFeedbacks().stream()
                    .map(FeedbackPassageiroMap::toEntity)
                    .collect(Collectors.toList());
            entity.setFeedbacks(feedbacks);
        }
    }

    public static LinhaDTO toSummary(Linha entity) {
        if (entity == null) {
            return null;
        }
        LinhaDTO dto = new LinhaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    public static Linha fromSummary(LinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        Linha entity = new Linha();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        return entity;
    }
}
