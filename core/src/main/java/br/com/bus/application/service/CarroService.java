package br.com.bus.application.service;

import java.util.List;

import br.com.bus.application.dto.CarroDTO;
import br.com.bus.application.service.useCase.AtualizarCarro;
import br.com.bus.application.service.useCase.BuscaCarro;
import br.com.bus.application.service.useCase.CriarCarro;
import br.com.bus.application.service.useCase.DeletarCarro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CarroService {

    @Inject
    BuscaCarro buscaCarro;

    @Inject
    CriarCarro criarCarro;

    @Inject
    AtualizarCarro atualizarCarro;

    @Inject
    DeletarCarro deletarCarro;

    public List<CarroDTO> listar(int page, int size) {
        return buscaCarro.listar(page, size);
    }

    public CarroDTO buscarPorId(Long id) {
        return buscaCarro.porId(id);
    }

    public CarroDTO criar(CarroDTO dto) {
        return criarCarro.executar(dto);
    }

    public CarroDTO atualizar(Long id, CarroDTO dto) {
        return atualizarCarro.executar(id, dto);
    }

    public void deletar(Long id) {
        deletarCarro.executar(id);
    }
}
