package br.com.bus.repository;

import br.com.bus.domain.manutencaoPeca.ManutencaoPeca;
import br.com.bus.domain.manutencaoPeca.ManutencaoPecaId;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ManutencaoPecaRepository implements PanacheRepositoryBase<ManutencaoPeca, ManutencaoPecaId> {
}
