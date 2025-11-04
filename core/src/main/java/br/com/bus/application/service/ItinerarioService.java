package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.service.useCase.AtualizarItinerario;
import br.com.bus.application.service.useCase.BuscaItinerario;
import br.com.bus.application.service.useCase.CriarItinerario;
import br.com.bus.application.service.useCase.DeletarItinerario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ItinerarioService {

    @Inject
    BuscaItinerario busca;

    @Inject
    CriarItinerario criar;

    @Inject
    AtualizarItinerario atualizar;

    @Inject
    DeletarItinerario deletar;

    public List<ItinerarioDTO> listar(int page, int size) { return busca.listar(page, size); }
    public ItinerarioDTO buscarPorId(Short ordem, Long idLinha, Long idPontoParada) {
        return busca.porId(ordem, idLinha, idPontoParada);
    }
    public ItinerarioDTO criar(ItinerarioDTO dto) { return criar.executar(dto); }
    public ItinerarioDTO atualizar(Short ordem, Long idLinha, Long idPontoParada, ItinerarioDTO dto) {
        return atualizar.executar(ordem, idLinha, idPontoParada, dto);
    }
    public void deletar(Short ordem, Long idLinha, Long idPontoParada) { deletar.executar(ordem, idLinha, idPontoParada); }
}
