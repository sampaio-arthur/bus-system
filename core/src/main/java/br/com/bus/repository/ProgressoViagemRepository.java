package br.com.bus.repository;

import br.com.bus.domain.progressoViagem.ProgressoViagem;
import br.com.bus.domain.progressoViagem.ProgressoViagemId;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProgressoViagemRepository implements PanacheRepositoryBase<ProgressoViagem, ProgressoViagemId> {
}
