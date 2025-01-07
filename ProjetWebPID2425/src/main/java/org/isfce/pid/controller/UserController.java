package org.isfce.pid.controller;


import java.util.List;

import org.isfce.pid.model.dto.UserDto;
import org.isfce.pid.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping(path = "/incsolde" )
	public ResponseEntity<Double> updateSolde(@RequestParam("username") String username,
			@RequestParam("montant") Double montant) {
		log.info("Crediter: "+username+" montant: "+montant);
		Double solde = userService.crediterUser(username, montant);
		return ResponseEntity.ok(solde);
	}
	
	
	//@PreAuthorize(value = "hasAuthority('SCOPE_XXX')")
	@PreAuthorize(value = "hasAnyRole('ROLE_CAFET')")
	@GetMapping(params="all")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok( userService.getAllUserDto());
	}

}
