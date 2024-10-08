package org.isfce.pid.controller.exceptions;

//Map cette exception sur une erreur HTTP 404 
//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Elément non trouvé!")
public class NotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	// identifiant de l'objet recherche
	private String code;

	/**
	 * Exception qui indique que l'objet ayant le code indiqué n'existe pas message
	 * 
	 * @param message
	 * @param code
	 */
	public NotExistException(String message, String code) {
		super(message);
		this.code = code;
	}

	/**
	 * Exception qui indique que l'objet ayant le code indiqué n'existe pas message
	 * 
	 * @param message
	 * @param code
	 */
	public NotExistException(String code) {
		this("error.object.notExist", code);
	}

	public String getCode() {
		return code;
	}
}
