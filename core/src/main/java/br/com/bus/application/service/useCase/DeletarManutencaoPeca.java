package br.com.bus.application.service.useCase;

import br.com.bus.domain.manutencaoPeca.ManutencaoPecaId;
import br.com.bus.repository.ManutencaoPecaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeletarManutencaoPeca {

    @Inject
    ManutencaoPecaRepository repository;

    @Transactional
    public void executar(Long idManutencao, Long idPeca) {
        ManutencaoPecaId id = new ManutencaoPecaId(idManutencao, idPeca);
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException(
                    "Manutenção de peça não encontrada: manutencao=" + idManutencao + ", peca=" + idPeca);
        }
    }
}
