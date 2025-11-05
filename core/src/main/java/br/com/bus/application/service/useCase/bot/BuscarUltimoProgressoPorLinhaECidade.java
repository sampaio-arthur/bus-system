package br.com.bus.application.service.useCase.bot;

import br.com.bus.application.dto.ProgressoViagemDTO;
import br.com.bus.application.mapper.ProgressoViagemMap;
import br.com.bus.domain.progressoViagem.ProgressoViagem;
import br.com.bus.repository.ProgressoViagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BuscarUltimoProgressoPorLinhaECidade {

    @Inject
    ProgressoViagemRepository progressoViagemRepository;

    public ProgressoViagemDTO executar(String nomeLinha, String nomeCidade) {
        String cidade = nomeCidade == null ? "" : nomeCidade.toLowerCase();
        String linha = nomeLinha == null ? "" : nomeLinha.toLowerCase();

        ProgressoViagem pv = progressoViagemRepository.find(
                        "select pv from ProgressoViagem pv " +
                                "join pv.viagem v " +
                                "join v.linha l " +
                                "join pv.pontoParada pp " +
                                "join pp.cidade c " +
                                "where lower(l.nome) = ?1 and lower(c.nome) = ?2 " +
                                "order by pv.id.data desc",
                        linha, cidade)
                .firstResultOptional()
                .orElseThrow(() -> new NotFoundException("Nenhum progresso encontrado para a linha na cidade informada"));

        return ProgressoViagemMap.toDTO(pv);
    }
}

