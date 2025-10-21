package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.MotoristaDTO;
import br.com.bus.application.service.useCase.AtualizarMotorista;
import br.com.bus.application.service.useCase.BuscaMotorista;
import br.com.bus.application.service.useCase.CriarMotorista;
import br.com.bus.application.service.useCase.DeletarMotorista;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MotoristaService {

    @Inject
    BuscaMotorista busca;

    @Inject
    CriarMotorista criar;

    @Inject
    AtualizarMotorista atualizar;

    @Inject
    DeletarMotorista deletar;

    public List<MotoristaDTO> listar(int page, int size) { return busca.listar(page, size); }
    public MotoristaDTO buscarPorId(Long id) { return busca.porId(id); }
    public MotoristaDTO criar(MotoristaDTO dto) { return criar.executar(dto); }
    public MotoristaDTO atualizar(Long id, MotoristaDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

