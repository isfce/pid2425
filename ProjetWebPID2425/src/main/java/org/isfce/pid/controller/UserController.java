package org.isfce.pid.controller;

import java.util.List;

import org.isfce.pid.model.User;
import org.isfce.pid.model.dto.UserDto;
import org.isfce.pid.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/user", produces = "application/json")
@CrossOrigin("*")
@Slf4j
public class UserController {

	UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping(path = "/incsolde")
	public ResponseEntity<Double> updateSolde(@RequestParam("username") String username,
			@RequestParam("montant") Double montant) {
		log.info("Crediter: " + username + " montant: " + montant);
		Double solde = userService.crediterUser(username, montant);
		return ResponseEntity.ok(solde);
	}

	@PreAuthorize(value = "hasAnyRole('CAFET','ADMIN')")
	@GetMapping(params = "all")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUserDto());
	}

	@GetMapping("/profile/{id}")
	@PreAuthorize(value = "hasRole('CAFET') or #username == authentication.name")
	public ResponseEntity<UserDto> getUserInfo(@PathVariable("id") String username, JwtAuthenticationToken auth) {
		var oUser = userService.getUserById(username);
		
		User user = oUser.orElse(null);
		
		// Crée l'utilisateur s'il n'existe pas et que
		// l'utilisateur connecté correspond au username
		if (oUser.isEmpty() && auth.getName().equals(username)) {
			// crée un utilisateur avec son solde à 0
			var token = auth.getToken();
			String email = token.getClaimAsString("email");
			String nom = token.getClaimAsString("family_name");
			String prenom = token.getClaimAsString("given_name");
			user = userService.addUser(new User(username, email, nom, prenom, 0.0));
		}
		if (user != null)
			return ResponseEntity.ok(new UserDto(username, user.getEmail(), user.getSolde()));
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
