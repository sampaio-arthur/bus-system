package br.com.bus.repository;

import br.com.bus.domain.Van;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VanRepository implements PanacheRepository<Van> {
}

