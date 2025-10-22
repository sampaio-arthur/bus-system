package br.com.bus.application.service.useCase;

import java.time.LocalDateTime;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.mapper.ProgressoViagemMap;
import br.com.bus.domain.progressoViagem.ProgressoViagem;
import br.com.bus.domain.progressoViagem.ProgressoViagemId;
import br.com.bus.repository.ProgressoViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarProgressoViagem {

    @Inject
    ProgressoViagemRepository repository;

    @Transactional
    public ProgressoViagemDTO executar(LocalDateTime data, Long idViagem, Long idPontoParada, ProgressoViagemDTO dto) {
        ProgressoViagemId id = new ProgressoViagemId();
        id.setData(data);
        id.setIdViagem(idViagem);
        id.setIdPontoParada(idPontoParada);
        ProgressoViagem entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException(
                        "Progresso de viagem não encontrado: viagem=" + idViagem
                                + ", ponto=" + idPontoParada + ", data=" + data));
        ProgressoViagemMap.updateEntityFromDTO(dto, entity);
        return ProgressoViagemMap.toDTO(entity);
    }
}
