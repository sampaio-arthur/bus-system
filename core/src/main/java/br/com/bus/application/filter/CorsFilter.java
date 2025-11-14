package br.com.bus.application.filter;

import jakarta.annotation.Priority;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Provider
@Singleton
@Priority(Priorities.HEADER_DECORATOR)
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private final List<String> allowedOrigins;
    private final String allowedMethods;
    private final String allowedHeaders;
    private final boolean allowCredentials;

    public CorsFilter(
            @ConfigProperty(name = "cors.origins", defaultValue = "*") String origins,
            @ConfigProperty(name = "cors.methods", defaultValue = "GET,POST,PUT,DELETE,OPTIONS") String methods,
            @ConfigProperty(name = "cors.headers", defaultValue = "Authorization,Content-Type,Accept") String headers,
            @ConfigProperty(name = "cors.allow-credentials", defaultValue = "true") boolean allowCredentials) {
        this.allowedOrigins = Arrays.stream(origins.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        this.allowedMethods = methods;
        this.allowedHeaders = headers;
        this.allowCredentials = allowCredentials;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())
                && requestContext.getHeaderString("Origin") != null
                && requestContext.getHeaderString("Access-Control-Request-Method") != null) {
            requestContext.abortWith(Response.ok().build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        String origin = requestContext.getHeaderString("Origin");
        String value = resolveOrigin(origin);
        if (value == null) {
            return;
        }

        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", value);
        responseContext.getHeaders().putSingle("Vary", "Origin");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", allowedMethods);
        responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", allowedHeaders);
        if (allowCredentials) {
            responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        }
    }

    private String resolveOrigin(String origin) {
        if (allowedOrigins.isEmpty()) {
            return null;
        }
        if (allowedOrigins.contains("*")) {
            return origin == null ? "*" : origin;
        }
        if (origin != null && allowedOrigins.stream().anyMatch(origin::equalsIgnoreCase)) {
            return origin;
        }
        return null;
    }
}
