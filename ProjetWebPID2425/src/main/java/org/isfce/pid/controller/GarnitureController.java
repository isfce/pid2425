package org.isfce.pid.controller;

import java.util.List;

import org.isfce.pid.dao.IGarnitureJpaDao;
import org.isfce.pid.model.Garniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/garniture", produces = "application/json")
public class GarnitureController {
	@Autowired
	IGarnitureJpaDao dao;

	@GetMapping(params = "all")
	List<Garniture> getListeGarniture() {
		return dao.findAll();
	}

	@GetMapping(params = "dispo")
	List<Garniture> getListeGarnitureDispo() {
		return dao.garnitureDiponibles2(true);
	}
}
