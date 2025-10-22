package br.com.bus.application.service.useCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.mapper.ProgressoViagemMap;
import br.com.bus.domain.progressoViagem.ProgressoViagemId;
import br.com.bus.repository.ProgressoViagemRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaProgressoViagem {

    @Inject
    ProgressoViagemRepository repository;

    public ProgressoViagemDTO porId(LocalDateTime data, Long idViagem, Long idPontoParada) {
        ProgressoViagemId id = new ProgressoViagemId();
        id.setData(data);
        id.setIdViagem(idViagem);
        id.setIdPontoParada(idPontoParada);
        return ProgressoViagemMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Progresso de viagem não encontrado: viagem=" + idViagem
                                        + ", ponto=" + idPontoParada + ", data=" + data))
        );
    }

    public List<ProgressoViagemDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(ProgressoViagemMap::toSummary)
                .collect(Collectors.toList());
    }
}
