package br.com.bus.repository;

import br.com.bus.domain.Passagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PassagemRepository implements PanacheRepository<Passagem> {
}

