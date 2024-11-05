package org.isfce.pid.controller;

import java.util.List;
import java.util.Optional;

import org.isfce.pid.dao.IGarnitureJpaDao;
import org.isfce.pid.model.Garniture;
import org.isfce.pid.model.RGarniture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/garniture", produces = "application/json")
public class GarnitureController {

	IGarnitureJpaDao dao;

	public GarnitureController(IGarnitureJpaDao dao) {
		this.dao = dao;
	}

	@GetMapping("/{code}")
	public ResponseEntity<Garniture> getGarniture(@PathVariable("code") String code) {
		Optional<Garniture> oGarn = dao.findById(code);

		if (oGarn.isPresent())
			return new ResponseEntity<>(oGarn.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping(params = "all")
	ResponseEntity<List<RGarniture>> getListeGarniture() {
		return new ResponseEntity<>(dao.listeGarniture(), HttpStatus.OK);
	}

	@GetMapping(params = "dispo")
	ResponseEntity<List<Garniture>> getListeGarnitureDispo(
			@RequestParam(name = "dispo", defaultValue = "true", required = false) boolean dispo) {
		return new ResponseEntity<>(dao.garnitureDiponibles2(dispo), HttpStatus.OK);
	}

	@PostMapping(path = "/add", consumes = "application/json") // précise le format du cours
	public ResponseEntity<Garniture> addGarniture(@Valid @RequestBody Garniture garniture) {
		garniture = dao.save(garniture);
		return ResponseEntity.ok(garniture);
	}

	@DeleteMapping("/{code}/delete")
	public ResponseEntity<String> deleteGarniture(@PathVariable("code") String code) {
		if (dao.existsById(code)) {
			dao.deleteById(code);
			return new ResponseEntity<>(code, HttpStatus.OK);
		} else
			return new ResponseEntity<>(code, HttpStatus.NOT_FOUND);
	}

}
