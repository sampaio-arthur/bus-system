package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.EmpresaTransporteDTO;
import br.com.bus.application.service.useCase.AtualizarEmpresaTransporte;
import br.com.bus.application.service.useCase.BuscaEmpresaTransporte;
import br.com.bus.application.service.useCase.CriarEmpresaTransporte;
import br.com.bus.application.service.useCase.DeletarEmpresaTransporte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmpresaTransporteService {

    @Inject
    BuscaEmpresaTransporte busca;

    @Inject
    CriarEmpresaTransporte criar;

    @Inject
    AtualizarEmpresaTransporte atualizar;

    @Inject
    DeletarEmpresaTransporte deletar;

    public List<EmpresaTransporteDTO> listar(int page, int size) { return busca.listar(page, size); }
    public EmpresaTransporteDTO buscarPorId(Long id) { return busca.porId(id); }
    public EmpresaTransporteDTO criar(EmpresaTransporteDTO dto) { return criar.executar(dto); }
    public EmpresaTransporteDTO atualizar(Long id, EmpresaTransporteDTO dto) { return atualizar.executar(id, dto); }
    public void deletar(Long id) { deletar.executar(id); }
}

