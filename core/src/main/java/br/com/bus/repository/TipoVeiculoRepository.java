package br.com.bus.repository;

import br.com.bus.domain.TipoVeiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoVeiculoRepository implements PanacheRepository<TipoVeiculo> {
}

