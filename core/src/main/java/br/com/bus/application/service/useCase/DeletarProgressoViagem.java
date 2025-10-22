package br.com.bus.application.service.useCase;

import java.time.LocalDateTime;

import br.com.bus.domain.progressoViagem.ProgressoViagemId;
import br.com.bus.repository.ProgressoViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarProgressoViagem {

    @Inject
    ProgressoViagemRepository repository;

    @Transactional
    public void executar(LocalDateTime data, Long idViagem, Long idPontoParada) {
        ProgressoViagemId id = new ProgressoViagemId();
        id.setData(data);
        id.setIdViagem(idViagem);
        id.setIdPontoParada(idPontoParada);
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException(
                    "Progresso de viagem não encontrado: viagem=" + idViagem
                            + ", ponto=" + idPontoParada + ", data=" + data);
        }
    }
}
