package br.com.bus.repository;

import br.com.bus.domain.Motorista;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MotoristaRepository implements PanacheRepository<Motorista> {
}

