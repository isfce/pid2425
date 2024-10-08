package org.isfce.pid.controller.exceptions;

import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Didier 
 * Une classe annotée par @ControllerAdvice permet de fournir pour tous les controleurs, 
 *         -des gestions (déviations) d'exceptions
 *         centalisées 
 *         -s'assurer que certaines clés soient présentes dans
 *         "model" avec @ModelAttribute 
 *         -créer des binders pour permettre une
 *         conversion de String vers une classe (ex: reconstruire une date à
 *         partir d'un texte)
 */
@ControllerAdvice
public class CentralExceptionHandle {
	/**
	 * Déviation des exceptions citées pour ouvrir une page personnalisée de type
	 * erreur Comme il n'est pas possible de fournir une Map à cette méthode, on
	 * doit créer un objet "ModelAndView"
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler({NoSuchElementException.class, NotExistException.class})
	private ModelAndView generalHandler(HttpServletRequest req, Exception e) {
		ModelAndView m = new ModelAndView();
		m.addObject("exception", e);
		m.addObject("path", req.getRequestURL());
		m.setViewName("error");//nom logique de la page d'erreur
		return m;
	}	
	
}
