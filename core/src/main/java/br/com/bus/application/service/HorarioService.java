package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.HorarioDTO;
import br.com.bus.application.service.useCase.AtualizarHorario;
import br.com.bus.application.service.useCase.BuscaHorario;
import br.com.bus.application.service.useCase.CriarHorario;
import br.com.bus.application.service.useCase.DeletarHorario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HorarioService {

    @Inject
    BuscaHorario busca;

    @Inject
    CriarHorario criar;

    @Inject
    AtualizarHorario atualizar;

    @Inject
    DeletarHorario deletar;

    public List<HorarioDTO> listar(int page, int size) { return busca.listar(page, size); }
    public HorarioDTO buscarPorId(Long id) { return busca.porId(id); }
    public HorarioDTO criar(HorarioDTO dto) { return criar.executar(dto); }
    public HorarioDTO atualizar(Long id, HorarioDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

