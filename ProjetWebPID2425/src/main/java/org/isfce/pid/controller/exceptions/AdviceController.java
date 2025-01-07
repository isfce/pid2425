package org.isfce.pid.controller.exceptions;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
