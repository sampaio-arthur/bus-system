package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.OnibusDTO;
import br.com.bus.application.service.useCase.AtualizarOnibus;
import br.com.bus.application.service.useCase.BuscaOnibus;
import br.com.bus.application.service.useCase.CriarOnibus;
import br.com.bus.application.service.useCase.DeletarOnibus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OnibusService {

    @Inject
    BuscaOnibus busca;

    @Inject
    CriarOnibus criar;

    @Inject
    AtualizarOnibus atualizar;

    @Inject
    DeletarOnibus deletar;

    public List<OnibusDTO> listar(int page, int size) { return busca.listar(page, size); }
    public OnibusDTO buscarPorId(Long id) { return busca.porId(id); }
    public OnibusDTO criar(OnibusDTO dto) { return criar.executar(dto); }
    public OnibusDTO atualizar(Long id, OnibusDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

