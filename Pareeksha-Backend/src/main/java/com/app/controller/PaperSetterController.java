package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthenticationRequest;
import com.app.dto.AuthenticationResponse;
import com.app.dto.PaperSetterDto;
import com.app.pojos.PaperSetter;
import com.app.service.IPaperSetter;
import com.app.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/papersetter")
public class PaperSetterController {

	@Autowired
	IPaperSetter service;
	@Autowired
	private AuthenticationManager mgr;
	@Autowired
	private UserDetailsService UserDetailsService;
	@Autowired
	private JwtUtil utils;
	
	public PaperSetterController() {
		System.out.println("In Constructor of "+getClass().getName());
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpPaperSetter(@RequestBody @Valid PaperSetterDto dto,PaperSetter transientObj){
		System.out.println("In signUpPaperSetter()");
		System.out.println(dto);
		transientObj.setName(dto.getName()); 
		transientObj.setEmail(dto.getEmail());
		transientObj.setDob(dto.getDob());
		transientObj.setPassword(dto.getPassword());
		System.out.println(transientObj);
		try {
			PaperSetter savedPaperSetter = service.savePaperSetter(transientObj);
			return new ResponseEntity<>(savedPaperSetter, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in save " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// empty body content , sending only err code
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createJwtToken(@RequestBody AuthenticationRequest req) {
		try {
			mgr.authenticate(new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword()));
			System.out.println("authenticate successfull");
		} catch (BadCredentialsException e) {
			throw new RuntimeException("Invalid UserName or password");
		}
		// authentication successful : return JWT token to the client
		UserDetails details = UserDetailsService.loadUserByUsername(req.getUserName());
		return ResponseEntity.ok(new AuthenticationResponse(utils.generateToken(details)));

	}
}
