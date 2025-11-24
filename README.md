# 🚌 Bus System – Sistema Integrado de Gerenciamento de Transporte Coletivo (SIGTC)

### Projeto desenvolvido para a disciplina **DEC7588 – Banco de Dados**  
**Universidade Federal de Santa Catarina – Campus Araranguá**  
**Professor:** Alexandre Leopoldo Gonçalves  

**Autores:**  
- Arthur Silveira Sampaio (24103786)  
- Diego Nyland Bloemer (24103789)  
---

# 📌 Visão Geral

O **Bus System** é um sistema completo para **gerenciamento de transporte coletivo**, permitindo às empresas controlarem:

- Frota e manutenção  
- Motoristas, mecânicos e passageiros  
- Linhas, itinerários e pontos de parada  
- Pontos turísticos próximos aos pontos  
- Cronogramas de viagens  
- Execução e monitoramento de viagens  
- Vendas de passagens  
- Geração de insights via IA (módulo opcional)  

Toda a execução é **100% em containers**, sem necessidade de instalar Java, Node ou PostgreSQL na máquina.

O projeto foi pensado academicamente, mas com arquitetura profissional escalável e pronta para futura oferta **SaaS**.

---

# 🧱 Estrutura do Projeto

```
bus-system/
│
├── core/                        → Back-end Quarkus (API REST + Panache + JWT)
│   ├── src/
│   └── pom.xml
│
├── front/                       → Front-end (Vite + React + TypeScript)
│   ├── src/
│   └── package.json
│
├── bot/                         → Módulo de IA e automações
│   ├── ai-compose.yml           → Sobe RabbitMQ + Redis + n8n
│   ├── n8n-flows/               → Fluxos JSON para importação
│   └── README.md                → Instruções opcionais
│
│
├── docker-compose.yml           → Compose principal (core + front + postgres)
├── .env.example                 → Modelo de variáveis de ambiente
└── README.md                    → Este documento
```

---

# 🐳 Execução do Sistema (100% via Docker)

### ▶️ Passo 1 — Configurar variáveis

Na raiz do projeto:

```
cp .env.example .env
```

### ▶️ Passo 2 — Subir o sistema completo (core + front + banco)

```
docker compose up -d
```

Isso automaticamente inicia:

- Back-end Quarkus  
- Front-end React/Vite  
- PostgreSQL  

Não é necessário instalar dependência alguma no computador.

---

# 🤖 Módulo de IA (Opcional)

Entre na pasta:

```
/bot
```

E suba:

```
docker compose -f ai-compose.yml up -d
```

Isso sobe:

- RabbitMQ  
- Redis  
- n8n  

Os fluxos prontos para importação estão em:

```
/bot/n8n-flows/*.json
```

---

# 🛢️ Banco de Dados

O banco é **PostgreSQL** e contém:

- Modelagem Conceitual  
- Modelagem Lógica  
- Script DDL completo  
- Scripts de Seed (RF15)  
- Consultas SQL analíticas  

Esses scripts são acionados **automaticamente via endpoints administrativos** (abaixo).

---

# 🔧 Endpoints Administrativos (DDL, DML, CLEAN, RELOAD)

### Header obrigatório:

```
X-Admin-Token: X05D5wziCBb8kIvctd5Bq5IZBJp9abYM0HePXKvVKSyScLecZLAAHwiUs0RdtzgcfCK
```

A variável usada é:

```
DB_ADMIN_TOKEN
```

---

## ▶️ 1. Executar Migrations (DDL)  
**POST** `/admin/db/migrate`

Resposta:

```json
{
  "action": "migrate",
  "status": "applied",
  "count": <qtd_migrations>
}
```

---

## ▶️ 2. Popular o Banco (Seed – DML inicial)  
**POST** `/admin/db/seed`

Resposta:

```json
{
  "action": "seed",
  "status": "executed",
  "count": <qtd_statements>
}
```

---

## ▶️ 3. Limpar o Banco (truncate total)  
**POST** `/admin/db/clean`

Resposta:

```json
{
  "action": "clean",
  "status": "done",
  "count": 0
}
```

---

## ▶️ 4. Resetar completamente o banco (clean + migrate + seed)  
**POST** `/admin/db/reload`

Resposta:

```json
{
  "action": "reload",
  "status": "migrated+seeded",
  "count": <total>
}
```

---

# 🧭 Implementação dos Endpoints (para documentação)

```java
@Path("/admin/db")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DatabaseAdminController {

    @Inject
    DatabaseAdminService service;

    private void authorize(String token) {
        String expected = System.getenv("DB_ADMIN_TOKEN");
        if (expected != null && !expected.isBlank()) {
            if (token == null || !expected.equals(token)) {
                throw new jakarta.ws.rs.ForbiddenException("Invalid admin token");
            }
        }
    }

    @POST
    @Path("/migrate")
    public Response migrate(@HeaderParam("X-Admin-Token") String token) {
        authorize(token);
        int migrations = service.migrate();
        return Response.ok().entity(new Result("migrate", "applied", migrations)).build();
    }

    @POST
    @Path("/seed")
    public Response seed(@HeaderParam("X-Admin-Token") String token) {
        authorize(token);
        int statements = service.seed();
        return Response.ok().entity(new Result("seed", "executed", statements)).build();
    }

    @POST
    @Path("/clean")
    public Response clean(@HeaderParam("X-Admin-Token") String token) {
        authorize(token);
        service.clean();
        return Response.ok().entity(new Result("clean", "done", 0)).build();
    }

    @POST
    @Path("/reload")
    public Response reload(@HeaderParam("X-Admin-Token") String token) {
        authorize(token);
        service.clean();
        int migrations = service.migrate();
        int statements = service.seed();
        return Response.ok().entity(new Result("reload", "migrated+seeded", migrations + statements)).build();
    }

    public static class Result {
        public String action;
        public String status;
        public int count;
        public Result() {}
        public Result(String action, String status, int count) {
            this.action = action;
            this.status = status;
            this.count = count;
        }
    }
}
```

---

# 📊 Consultas Analíticas

As consultas SQL usadas no relatório estão em:

```
/database/analytics/
```

Incluindo:

1. Valor total gasto em peças por manutenção (últimos 12 meses)  
2. Pontos turísticos acessíveis por cidade  
3. Média de passageiros por viagem  

---

# 🌐 Repositório Oficial

👉 https://github.com/sampaio-arthur/bus-system/

---

# 🧾 Conclusão

O **Bus System** apresenta uma arquitetura moderna, modular, escalável e totalmente containerizada, possibilitando:

- Demonstrações rápidas  
- Ambiente de desenvolvimento simples  
- Expansão futura para SaaS  
- Integração direta com IA através do módulo **bot**  
- Administração completa do banco via endpoints internos  

O projeto integra conhecimentos de banco de dados, arquitetura e engenharia de software, seguindo boas práticas aplicadas no mercado atual.
