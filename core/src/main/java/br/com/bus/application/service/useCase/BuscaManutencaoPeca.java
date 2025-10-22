package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.ManutencaoPecaDTO;
import br.com.bus.application.mapper.ManutencaoPecaMap;
import br.com.bus.domain.manutencaoPeca.ManutencaoPecaId;
import br.com.bus.repository.ManutencaoPecaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaManutencaoPeca {

    @Inject
    ManutencaoPecaRepository repository;

    public ManutencaoPecaDTO porId(Integer idManutencao, Integer idPeca) {
        ManutencaoPecaId id = new ManutencaoPecaId(idManutencao, idPeca);
        return ManutencaoPecaMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Manutenção de peça não encontrada: manutencao=" + idManutencao + ", peca=" + idPeca))
        );
    }

    public List<ManutencaoPecaDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(ManutencaoPecaMap::toSummary)
                .collect(Collectors.toList());
    }
}
