package br.com.bus.repository;

import br.com.bus.domain.Viagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ViagemRepository implements PanacheRepository<Viagem> {
}

