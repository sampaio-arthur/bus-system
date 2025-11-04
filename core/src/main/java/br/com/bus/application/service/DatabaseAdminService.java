package br.com.bus.application.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.flywaydb.core.Flyway;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DatabaseAdminService {

    @Inject
    Flyway flyway;

    @Inject
    EntityManager em;

    public int migrate() {
        var result = flyway.migrate();
        return result.migrationsExecuted;
    }

    public void clean() {
        flyway.clean();
    }

    @Transactional
    public int seed() {
        int executed = 0;
        // Prefer single file db/seed.sql; fallback to db/seed/seed.sql
        String[] candidates = new String[] { "db/seed.sql", "db/seed/seed.sql" };
        for (String resource : candidates) {
            executed += executeSqlResourceIfPresent(resource);
        }
        return executed;
    }

    private int executeSqlResourceIfPresent(String resourcePath) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                return 0;
            }
            String sql = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            return executeSqlStatements(sql);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar seed: " + resourcePath, e);
        }
    }

    private int executeSqlStatements(String sql) {
        // naive split by ';' (ignores edge cases inside strings)
        int executed = 0;
        for (String stmt : sql.split(";")) {
            String s = stmt.trim();
            if (s.isEmpty()) continue;
            em.createNativeQuery(s).executeUpdate();
            executed++;
        }
        return executed;
    }
}

