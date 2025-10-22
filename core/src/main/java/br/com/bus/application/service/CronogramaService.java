package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.CronogramaDTO;
import br.com.bus.application.service.useCase.AtualizarCronograma;
import br.com.bus.application.service.useCase.BuscaCronograma;
import br.com.bus.application.service.useCase.CriarCronograma;
import br.com.bus.application.service.useCase.DeletarCronograma;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CronogramaService {

    @Inject
    BuscaCronograma busca;

    @Inject
    CriarCronograma criar;

    @Inject
    AtualizarCronograma atualizar;

    @Inject
    DeletarCronograma deletar;

    public List<CronogramaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public CronogramaDTO buscarPorId(Long id) { return busca.porId(id); }
    public CronogramaDTO criar(CronogramaDTO dto) { return criar.executar(dto); }
    public CronogramaDTO atualizar(Long id, CronogramaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}
