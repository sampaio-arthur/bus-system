package br.com.bus.repository;

import br.com.bus.domain.PontoTuristico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PontoTuristicoRepository implements PanacheRepository<PontoTuristico> {
}

