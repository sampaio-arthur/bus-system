package br.com.bus.repository;

import br.com.bus.domain.Passageiro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PassageiroRepository implements PanacheRepository<Passageiro> {
}

