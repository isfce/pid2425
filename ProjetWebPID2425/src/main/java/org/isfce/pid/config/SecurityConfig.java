package org.isfce.pid.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String keySetUri;

	private final KeycloakJwtConverter converter;

	// constructeur qui injecte le convertisseur jwt vers security context
	public SecurityConfig(KeycloakJwtConverter converter) {
		super();
		this.converter = converter;
	}
	
	@Bean
	@Profile("dev")
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/h2/**");
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(t ->t .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(c->c.disable());
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()))

		.oauth2ResourceServer(c -> c.jwt(j -> j.jwkSetUri(keySetUri).jwtAuthenticationConverter(converter)));
		http.authorizeHttpRequests(c -> 
		c.requestMatchers("/api/garniture/**").hasAnyRole("USER")
		.requestMatchers("/api/sandwichs/**").hasAuthority("USER")
		.requestMatchers(HttpMethod.POST,"/api/user/incsolde/**").hasAnyRole("CAFET")
		.requestMatchers(HttpMethod.GET,"/api/user/**").hasAnyRole("USER")
		.anyRequest().authenticated());

		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setExposedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

}
