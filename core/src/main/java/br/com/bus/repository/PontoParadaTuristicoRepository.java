package br.com.bus.repository;

import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristico;
import br.com.bus.domain.pontoParadaTuristico.PontoParadaTuristicoId;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PontoParadaTuristicoRepository implements PanacheRepositoryBase<PontoParadaTuristico, PontoParadaTuristicoId> {
}
