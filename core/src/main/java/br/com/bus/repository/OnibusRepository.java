package br.com.bus.repository;

import br.com.bus.domain.Onibus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OnibusRepository implements PanacheRepository<Onibus> {
}

