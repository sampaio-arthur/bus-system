package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bus.application.dto.MetodoPagamentoDTO;
import br.com.bus.application.mapper.MetodoPagamentoMap;
import br.com.bus.repository.MetodoPagamentoRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscaMetodoPagamento {

    @Inject
    MetodoPagamentoRepository repository;

    public MetodoPagamentoDTO porId(Long id) {
        return MetodoPagamentoMap.toDTO(
                repository.findByIdOptional(id)
                        .orElseThrow(() -> new NotFoundException("Método de pagamento não encontrado: id=" + id))
        );
    }

    public List<MetodoPagamentoDTO> listar(int page, int size) {
        return repository.findAll()
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(MetodoPagamentoMap::toSummary)
                .collect(Collectors.toList());
    }
}
