package org.isfce.pid.config;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class KeycloakJwtConverter implements Converter<Jwt, JwtAuthenticationToken> {

	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

	@Override
	public JwtAuthenticationToken convert(Jwt jwt) {

		// récupère les scopes et les roles pour les fusionner
		Set<GrantedAuthority> authorities = Stream
				.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
				.collect(Collectors.toSet());

		// récupère le username de token pour définir le nom du "principal"
		String preferredName = jwt.getClaimAsString("preferred_username");
		String principalName = Optional.of(preferredName).orElse(jwt.getClaimAsString(JwtClaimNames.SUB));

		return new JwtAuthenticationToken(jwt, authorities, principalName);
	}

	/**
	 * Extrait les roles de jwt de Keycloak qui se trouve dans l'entrée
	 * "realm_access" Rajoute le préfixe ROLE_ à chaque rôle
	 * 
	 * @param jwt
	 * @return
	 */
	private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt) {
		if (jwt.getClaim("realm_access") == null)
			return Set.of();
		Map<String, Object> realmAccess = jwt.getClaim("realm_access");
		
		@SuppressWarnings("unchecked")
		Collection<String> roles = (Collection<String>) realmAccess.get("roles");
		return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toSet());
	}

}
