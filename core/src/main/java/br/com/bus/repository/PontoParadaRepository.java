package br.com.bus.repository;

import br.com.bus.domain.PontoParada;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PontoParadaRepository implements PanacheRepository<PontoParada> {
}

