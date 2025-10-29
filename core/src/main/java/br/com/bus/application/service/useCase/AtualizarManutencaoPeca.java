package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ManutencaoPecaDTO;
import br.com.bus.application.mapper.ManutencaoPecaMap;
import br.com.bus.domain.Manutencao;
import br.com.bus.domain.Peca;
import br.com.bus.domain.manutencaoPeca.ManutencaoPeca;
import br.com.bus.domain.manutencaoPeca.ManutencaoPecaId;
import br.com.bus.repository.ManutencaoPecaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarManutencaoPeca {

    @Inject
    ManutencaoPecaRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public ManutencaoPecaDTO executar(Long idManutencao, Long idPeca, ManutencaoPecaDTO dto) {
        ManutencaoPecaId id = new ManutencaoPecaId(idManutencao, idPeca);
        ManutencaoPeca entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException(
                        "Manutenção de peça não encontrada: manutencao=" + idManutencao + ", peca=" + idPeca));
        aplicarAtualizacoes(entity, dto);
        return ManutencaoPecaMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(ManutencaoPeca entity, ManutencaoPecaDTO dto) {
        if (dto == null) {
            return;
        }
        ManutencaoPecaId identificador = entity.getId();
        if (identificador == null) {
            identificador = new ManutencaoPecaId();
            entity.setId(identificador);
        }
        entity.setQuantidadeUtilizada(dto.getQuantidadeUtilizada());
        entity.setValorUnitario(dto.getValorUnitario());

        if (dto.getIdManutencao() != null) {
            identificador.setIdManutencao(dto.getIdManutencao());
            entity.setManutencao(entityManager.getReference(Manutencao.class, dto.getIdManutencao()));
        }
        if (dto.getIdPeca() != null) {
            identificador.setIdPeca(dto.getIdPeca());
            entity.setPeca(entityManager.getReference(Peca.class, dto.getIdPeca()));
        }
    }
}
