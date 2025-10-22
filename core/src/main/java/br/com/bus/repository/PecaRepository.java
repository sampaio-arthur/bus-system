package br.com.bus.repository;

import br.com.bus.domain.Peca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PecaRepository implements PanacheRepository<Peca> {
}
