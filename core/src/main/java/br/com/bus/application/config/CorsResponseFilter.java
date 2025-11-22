package br.com.bus.application.config;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Forca headers de CORS para todas as respostas. Util quando a configuracao
 * padrao do Quarkus nao eh aplicada ou ha setups mistos (localhost vs. containers).
 */
@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class CorsResponseFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        // Trata preflight rapidamente e devolve ja com os headers de CORS.
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            request.abortWith(buildPreflightResponse(request.getHeaderString("Origin")));
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        applyCorsHeaders(responseContext, requestContext.getHeaderString("Origin"));
    }

    private Response buildPreflightResponse(String origin) {
        Response.ResponseBuilder builder = Response.ok();
        applyCorsHeaders(builder, origin);
        return builder.build();
    }

    private void applyCorsHeaders(Object response, String origin) {
        String allowedOrigin = origin != null && !origin.isBlank() ? origin : "*";
        if (response instanceof Response.ResponseBuilder) {
            Response.ResponseBuilder builder = (Response.ResponseBuilder) response;
            builder.header("Access-Control-Allow-Origin", allowedOrigin);
            builder.header("Vary", "Origin");
            builder.header("Access-Control-Allow-Credentials", "true");
            builder.header("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization, x-admin-token");
            builder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            builder.header("Access-Control-Max-Age", "86400");
        } else if (response instanceof ContainerResponseContext) {
            ContainerResponseContext context = (ContainerResponseContext) response;
            context.getHeaders().putSingle("Access-Control-Allow-Origin", allowedOrigin);
            context.getHeaders().putSingle("Vary", "Origin");
            context.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
            context.getHeaders().putSingle("Access-Control-Allow-Headers",
                    "origin, content-type, accept, authorization, x-admin-token");
            context.getHeaders().putSingle("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, OPTIONS");
            context.getHeaders().putSingle("Access-Control-Max-Age", "86400");
        }
    }
}
