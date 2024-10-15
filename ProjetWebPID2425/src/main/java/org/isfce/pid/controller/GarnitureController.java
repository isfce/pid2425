package org.isfce.pid.controller;

import org.isfce.pid.controller.exceptions.NotExistException;
import org.isfce.pid.dao.IGarnitureJpaDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/garniture")
public class GarnitureController {

	private IGarnitureJpaDao dao;

	// injection de l'accès au service
	public GarnitureController(IGarnitureJpaDao dao) {
		this.dao = dao;
	}

	/**
	 * Liste des garnitures
	 * 
	 * @param model
	 * @return la nom logique de la vue qui affichera la liste des garnitures
	 */
	@GetMapping("/liste")
	public String listeGarnitures(Model model) {
		model.addAttribute("garnituresList", dao.findAll());
		model.addAttribute("ecole", "ISFCE");
		return "garniture/listeGarnitures";
	}

	

	//
	/**
	 * Affiche le détail d'une garniture
	 * 
	 * @param code
	 * @param model
	 * @param locale
	 * @return
	 */
	@GetMapping("/{code}")
	public String detailGarniture(@PathVariable("code") String code, Model model) {
		log.info("Recherche la garniture: " + code);
		// si ce n'est pas une redirection, charge la garniture
		if (!model.containsAttribute("garniture"))
			// recherche la garniture dans la liste et ajout au model
			model.addAttribute("garniture",	dao.findById(code).orElseThrow(()->new NotExistException("Cette garniture n'existe pas")));
		return "garniture/garniture";
	}

}
