package br.com.bus.application.service.useCase;

import java.util.List;
import java.util.ArrayList;

import br.com.bus.application.dto.PontoTuristicoDTO;
import br.com.bus.application.mapper.PontoTuristicoMap;
import br.com.bus.domain.PontoParada;
import br.com.bus.domain.PontoTuristico;
import br.com.bus.repository.PontoTuristicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AtualizarPontoTuristico {

    @Inject
    PontoTuristicoRepository repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public PontoTuristicoDTO executar(Long id, PontoTuristicoDTO dto) {
        PontoTuristico entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Ponto turístico não encontrado: id=" + id));
        aplicarAtualizacoes(entity, dto);
        return PontoTuristicoMap.toDTO(entity);
    }

    private void aplicarAtualizacoes(PontoTuristico entity, PontoTuristicoDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAtivo(dto.getAtivo());

        if (dto.getPontosParadaProximos() != null) {
            // Precisamos atualizar o lado "owner" do relacionamento (PontoParada)
            // para que o join table seja persistido corretamente.
            List<PontoParada> atuais = new ArrayList<>(entity.getPontosParadaProximos());
            // Remove vínculo antigo
            atuais.forEach(p -> p.getPontosTuristicosProximos().remove(entity));

            List<PontoParada> novos = new ArrayList<>();
            dto.getPontosParadaProximos().stream()
                    .filter(p -> p.getId() != null)
                    .map(p -> entityManager.getReference(PontoParada.class, p.getId()))
                    .forEach(ponto -> {
                        novos.add(ponto);
                        if (!ponto.getPontosTuristicosProximos().contains(entity)) {
                            ponto.getPontosTuristicosProximos().add(entity);
                        }
                    });

            entity.getPontosParadaProximos().clear();
            entity.getPontosParadaProximos().addAll(novos);
        }
    }
}
