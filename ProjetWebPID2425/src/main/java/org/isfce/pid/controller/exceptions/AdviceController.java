package org.isfce.pid.controller.exceptions;

import java.sql.SQLException;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice
public class AdviceController {
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> erreursBD(SQLException exc){
		return new ResponseEntity<String>("DB Erreurs",HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NotExistException.class)
	public ResponseEntity<String> erreurNotFound(NotExistException esc) {
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	/**
	 * Permet de capturer les erreurs de validation et de retourner un objet avec les erreurs
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
		
        log.error("ERREUR de Validations: "+errors);
        ErrorResponse errorResponse = new ErrorResponse("Validation failed", errors.stream().map(e->e.getInvalidValue()).toList().toString());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
