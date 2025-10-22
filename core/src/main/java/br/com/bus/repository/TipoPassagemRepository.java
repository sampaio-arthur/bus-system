package br.com.bus.repository;

import br.com.bus.domain.TipoPassagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoPassagemRepository implements PanacheRepository<TipoPassagem> {
}
