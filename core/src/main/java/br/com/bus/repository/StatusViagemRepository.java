package br.com.bus.repository;

import br.com.bus.domain.StatusViagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatusViagemRepository implements PanacheRepository<StatusViagem> {
}

