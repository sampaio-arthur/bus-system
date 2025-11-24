package br.com.bus.application.service.useCase;

import br.com.bus.application.dto.ItinerarioDTO;
import br.com.bus.application.mapper.ItinerarioMap;
import br.com.bus.domain.Linha;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.itinerario.Itinerario;
import br.com.bus.domain.itinerario.ItinerarioId;
import br.com.bus.repository.ItinerarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarItinerario {

    @Inject
    ItinerarioRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public ItinerarioDTO executar(Short ordem, Long idLinha, Long idPontoParada, ItinerarioDTO dto) {
        ItinerarioId idAntigo = new ItinerarioId();
        idAntigo.setOrdem(ordem);
        idAntigo.setIdLinha(idLinha);
        idAntigo.setIdPontoParada(idPontoParada);

        Itinerario existente = repository.findByIdOptional(idAntigo)
                .orElseThrow(() -> new NotFoundException(
                        "Itinerario nao encontrado: ordem=" + ordem + ", linha=" + idLinha + ", ponto=" + idPontoParada));

        Short novaOrdem = dto != null && dto.getOrdem() != null ? dto.getOrdem() : idAntigo.getOrdem();
        Long novaLinha = dto != null && dto.getIdLinha() != null ? dto.getIdLinha() : idAntigo.getIdLinha();
        Long novoPonto = dto != null && dto.getIdPontoParada() != null ? dto.getIdPontoParada() : idAntigo.getIdPontoParada();

        boolean mudouChave = !idAntigo.getOrdem().equals(novaOrdem)
                || !idAntigo.getIdLinha().equals(novaLinha)
                || !idAntigo.getIdPontoParada().equals(novoPonto);

        if (mudouChave) {
            repository.delete(existente);
            ItinerarioDTO novoDto = new ItinerarioDTO();
            novoDto.setOrdem(novaOrdem);
            novoDto.setIdLinha(novaLinha);
            novoDto.setIdPontoParada(novoPonto);

            Itinerario novo = ItinerarioMap.toEntity(novoDto);
            novo.setLinha(entityManager.getReference(Linha.class, novaLinha));
            novo.setPontoParada(entityManager.getReference(PontoParada.class, novoPonto));
            repository.persist(novo);
            return ItinerarioMap.toDTO(novo);
        }

        existente.setLinha(entityManager.getReference(Linha.class, novaLinha));
        existente.setPontoParada(entityManager.getReference(PontoParada.class, novoPonto));
        return ItinerarioMap.toDTO(existente);
    }
}
