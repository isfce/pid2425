package org.isfce.pid.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.Optional;

import org.isfce.pid.model.User;
import org.isfce.pid.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest // lance le contexte Spring
@AutoConfigureMockMvc // Crée un mock mvc
@ActiveProfiles(profiles = "testU") // active le profile "testU"
@Sql(scripts = { "/dataTestU.sql" }, config = @SqlConfig(encoding = "utf-8")
// fichier SQL avec les données pour les tests
//permet de préciser d'autres paramètres de configuration
//,config = @SqlConfig(encoding = "utf-8", transactionMode =TransactionMode.ISOLATED)
)

class TestUserController {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private UserService userServiceMock;

	@BeforeEach
	void setUp() {
		// Configuration du mock pour renvoyer un utilisateur spécifique
		User et1 = new User("et1", "et1@isfce.be", "Nom Et1", "Prénom Et1", 50.3);
		User et2 = new User("et2", "et2@isfce.be", "Nom Et2", "Prénom Et2", 0.0);
		User val = new User("val", "val@isfce.be", "DeLaCafet", "Valérie", 0.0);

//		when(userServiceMock.existByUsername("et1")).thenReturn(true);
//		when(userServiceMock.existByUsername("et2")).thenReturn(false);
//		when(userServiceMock.existByUsername("val")).thenReturn(true);

		when(userServiceMock.getUserById("et1")).thenReturn(Optional.of(et1));
		when(userServiceMock.getUserById("et2")).thenReturn(Optional.empty());
		when(userServiceMock.getUserById("val")).thenReturn(Optional.of(val));

		when(userServiceMock.addUser(et2)).thenReturn(et2);
	}

	@Test
	@Disabled
	void testUpdateSolde() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testGetAllUser() {
		fail("Not yet implemented");
	}

	@Test //
	void testGetUserInfoEt1() throws Exception {
		Jwt jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", "et1")
				.claim("scope", "openid email profile").claim("preferred_username", "et1")
				.claim("given_name", "Prénom Et1").claim("family_name", "Nom Et1").claim("email", "et1@isfce.be")
				.build();
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities);

		mockMvc.perform(get("/api/user/profile/et1").with(authentication(token))).andExpect(status().isOk())
				.andExpect(jsonPath("username").value("et1")).andExpect(jsonPath("email").value("et1@isfce.be"))
				.andExpect(jsonPath("solde").value("50.3"));
		
		verify(userServiceMock).getUserById("et1");
		verify(userServiceMock,times(0)).addUser(null);
	}

	@Test
	void testGetUserInfoNew() throws Exception {
		User et2 = new User("et2", "et2@isfce.be", "Nom Et2", "Prénom Et2", 0.0);
		Jwt jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", "et2")
				.claim("scope", "openid email profile").claim("preferred_username", "et2")
				.claim("given_name", "Prénom Et2").claim("family_name", "Nom Et2").claim("email", "et2@isfce.be")
				.build();
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities);

		mockMvc.perform(get("/api/user/profile/et2").with(authentication(token))).andExpect(status().isOk())
				.andExpect(jsonPath("username").value("et2")).andExpect(jsonPath("email").value("et2@isfce.be"))
				.andExpect(jsonPath("solde").value("0.0"));
		verify(userServiceMock).getUserById("et2");
		verify(userServiceMock).addUser(et2);
	}

	@Test //
	void testGetUserInfoEt1FromVal() throws Exception {
		Jwt jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", "")
				.claim("scope", "openid email profile").claim("preferred_username", "val")
				.claim("given_name", "Valérie").claim("family_name", "DeLaCafet").claim("email", "val@isfce.be")
				.build();
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CAFET");
		JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities);

		mockMvc.perform(get("/api/user/profile/et1").with(authentication(token))).andExpect(status().isOk())
				.andExpect(jsonPath("username").value("et1")).andExpect(jsonPath("email").value("et1@isfce.be"))
				.andExpect(jsonPath("solde").value("50.3"));
		verify(userServiceMock).getUserById("et1");
		verify(userServiceMock,times(0)).addUser(null);
	}
	
	@Test //info d'user qui n'existe pas
	void testGetUserInfoBrolFromVal() throws Exception {
		Jwt jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", "")
				.claim("scope", "openid email profile").claim("preferred_username", "val")
				.claim("given_name", "Valérie").claim("family_name", "DeLaCafet").claim("email", "val@isfce.be")
				.build();
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CAFET");
		JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities);

		mockMvc.perform(get("/api/user/profile/brol").with(authentication(token)))
		.andExpect(status().isNotFound());
				
		verify(userServiceMock).getUserById("brol");
		verify(userServiceMock,times(0)).addUser(null);
	}
}
