package org.isfce.pid.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class HomeController {
	@Value("${spring.thymeleaf.encoding}")
	String encodage;
	
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("encodage", encodage);

		return "home";
	}
}
