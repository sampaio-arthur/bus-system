package br.com.bus.repository;

import br.com.bus.domain.Rota;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RotaRepository implements PanacheRepository<Rota> {
}

