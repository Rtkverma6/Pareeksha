package com.app.exception.handler;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

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
import com.app.exception.DataIntegrityViolationException;

@ControllerAdvice //tells its Spring bean
public class GlobalExcHandler extends ResponseEntityExceptionHandler {

	//exce handling methods
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException e,WebRequest request) {
		System.out.println("In Handling data access exception");
		ErrorResponse resp= new ErrorResponse(e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e,WebRequest request){
		System.out.println("In Handling No Such Element Exception");
		ErrorResponse resp = new ErrorResponse(e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e,WebRequest request) {
		System.out.println("In Handling Illegal Argument Exception");
		ErrorResponse resp= new ErrorResponse(e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationExc(ConstraintViolationException e, WebRequest request) {
		System.out.println("handling constraint violation exc");
		ErrorResponse errResp = new ErrorResponse(e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errResp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request) {
		System.out.println("handling constraint violation exc");
		ErrorResponse errResp = new ErrorResponse(e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errResp, HttpStatus.BAD_REQUEST);
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
	
	// handle ANY other exception
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleAnyException(Exception exc, WebRequest request) {
			System.out.println("in handle exc");
			ErrorResponse errResp = new ErrorResponse(exc.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errResp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
}
