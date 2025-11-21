package br.com.bus.application.config;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Força headers de CORS para todas as respostas. Útil quando a
 * configuração padrão do Quarkus não é aplicada ou há setups mistos
 * (localhost vs. containers).
 */
@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class CorsResponseFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        // Trata preflight rapidamente
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            request.abortWith(jakarta.ws.rs.core.Response.ok().build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        String origin = requestContext.getHeaderString("Origin");
        // Se não vier Origin, devolve wildcard; se vier, ecoa o origin para facilitar credenciais/localhost.
        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin",
                origin != null && !origin.isBlank() ? origin : "*");
        responseContext.getHeaders().putSingle("Vary", "Origin");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        responseContext.getHeaders().putSingle("Access-Control-Max-Age", "86400");
    }
}
