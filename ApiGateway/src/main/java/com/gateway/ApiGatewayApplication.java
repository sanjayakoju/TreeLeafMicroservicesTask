package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth-service", r -> r.path("/auth/**", "/users/**")
						.uri("lb://AUTH-SERVICE"))
				.route("blog-service", r -> r.path("/blogs/**")
						.uri("lb://BLOG-SERVICE"))
				// Add a route for Swagger UI
				.route("swagger-ui", r -> r.path("/swagger-ui/**")
						.uri("lb://springdoc-openapi-ui"))
				.build();
	}


}
