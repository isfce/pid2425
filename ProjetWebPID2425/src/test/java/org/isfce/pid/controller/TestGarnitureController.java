package org.isfce.pid.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.isfce.pid.dao.IGarnitureDao;
import org.isfce.pid.model.Garniture;
import org.isfce.pid.model.RGarniture;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(GarnitureController.class)
public class TestGarnitureController {

	@MockitoBean
	IGarnitureDao dao;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "et13", roles = "BROL", password = "et1") // aucun contrôle
	public void testGetGarniture() throws Exception {
		// Programmation du mock
		Garniture sal = new Garniture("SAL", "Salade", true);
		when(dao.findById("SAL")).thenReturn(Optional.of(sal));
		// appel Get
		mockMvc.perform(get("/api/garniture/SAL")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("code").value("SAL"))
				.andExpect(jsonPath("nom").value("Salade"))
				.andExpect(jsonPath("disponible").value(true));

		// autre possibilité pour vérifier le contenu
		mockMvc.perform(get("/api/garniture/SAL")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")) // Vérifie que le contenu est du JSON
				.andExpect(content().json("{code: SAL, nom: Salade, disponible: true}")); // Vérifie le contenu JSON

		// Programmation du mock pour une garniture qui n'existe pas
		when(dao.findById("BRO")).thenReturn(Optional.empty());
		// appel Get
		mockMvc.perform(get("/api/garniture/BRO")).andExpect(status().isNotFound())
		.andExpect(content().string(""));
	}
	
	@Test
	@WithMockUser(username = "et1", roles = "BROL", password = "et1") // aucun contrôle
	public void testGetAllGarniture() throws Exception {
		// Programmation du mock
		RGarniture sal = new RGarniture("SAL", "Salade", true);
		RGarniture oeuf = new RGarniture("OE", "Oeuf", true);
		when(dao.listeGarniture()).thenReturn(List.of(sal,oeuf));
		
		// appel Get
		mockMvc.perform(get("/api/garniture?all")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray()) 
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("SAL"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("OE")) ;
	}

}
