package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ManutencaoPecaDTO;
import br.com.bus.application.mapper.ManutencaoPecaMap;
import br.com.bus.domain.manutencaoPeca.ManutencaoPeca;
import br.com.bus.domain.manutencaoPeca.ManutencaoPecaId;
import br.com.bus.repository.ManutencaoPecaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarManutencaoPeca {

    @Inject
    ManutencaoPecaRepository repository;

    @Transactional
    public ManutencaoPecaDTO executar(Integer idManutencao, Integer idPeca, ManutencaoPecaDTO dto) {
        ManutencaoPecaId id = new ManutencaoPecaId(idManutencao, idPeca);
        ManutencaoPeca entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException(
                        "Manutenção de peça não encontrada: manutencao=" + idManutencao + ", peca=" + idPeca));
        ManutencaoPecaMap.updateEntityFromDTO(dto, entity);
        return ManutencaoPecaMap.toDTO(entity);
    }
}
