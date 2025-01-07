package org.isfce.pid.controller;

import java.util.List;

import org.isfce.pid.dao.ISandwichDao;
import org.isfce.pid.model.Sandwiches;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/sandwichs", produces = "application/json")
@CrossOrigin("*")
public class SandwichsController {
	private ISandwichDao dao;

	public SandwichsController(ISandwichDao sandwichDao) {
		this.dao = sandwichDao;
	}

	@GetMapping(params = "all")
	ResponseEntity<List<Sandwiches>> getListeGarniture() {

		return new ResponseEntity<List<Sandwiches>>(dao.findAll(), HttpStatus.OK);
	}

}
