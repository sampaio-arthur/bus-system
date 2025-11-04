package br.com.bus.application.controller;

import br.com.bus.application.service.DatabaseAdminService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

