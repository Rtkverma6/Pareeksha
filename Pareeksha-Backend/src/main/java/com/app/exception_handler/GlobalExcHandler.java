package com.app.exception_handler;

import java.util.ArrayList;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.dto.ErrorResponse;

@ControllerAdvice //tells its Spring bean
public class GlobalExcHandler extends ResponseEntityExceptionHandler {

	//exce handling methods
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException e,WebRequest request) {
		System.out.println("In Handling data access exception");
		ErrorResponse resp= new ErrorResponse(e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("In Handling MethodArgumentNotValidn");
		ArrayList<String> errMesg = new ArrayList<>();
		
		for(FieldError err : ex.getBindingResult().getFieldErrors())
			errMesg.add(err.getDefaultMessage()); //popoulated list of error messages for failed validation rules
		ErrorResponse resp = new ErrorResponse("Validation Failure !!!",errMesg.toString());
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	
}
