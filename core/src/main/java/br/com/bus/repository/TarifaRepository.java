package br.com.bus.repository;

import br.com.bus.domain.Tarifa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarifaRepository implements PanacheRepository<Tarifa> {
}

