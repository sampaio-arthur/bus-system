package br.com.bus.repository;

import br.com.bus.domain.ParadaLinha;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParadaLinhaRepository implements PanacheRepository<ParadaLinha> {
}

