package br.com.bus.repository;

import br.com.bus.domain.FeedbackPassageiro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FeedbackPassageiroRepository implements PanacheRepository<FeedbackPassageiro> {
}

