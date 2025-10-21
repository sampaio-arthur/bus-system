package br.com.bus.repository;

import br.com.bus.domain.Horario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HorarioRepository implements PanacheRepository<Horario> {
}

