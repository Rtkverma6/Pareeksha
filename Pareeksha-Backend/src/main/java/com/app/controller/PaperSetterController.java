package com.app.controller;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cust_excs.DataIntegrityViolationException;
import com.app.cust_excs.IllegalArgumentException;
import com.app.dto.AuthenticationRequest;
import com.app.dto.AuthenticationResponse;
import com.app.dto.PaperSetterDto;
import com.app.mapper.PaperSetterMapper;
import com.app.pojos.PaperSetter;
import com.app.service.IPaperSetterService;
import com.app.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/papersetter")
public class PaperSetterController {

	@Autowired
	IPaperSetterService service;
	@Autowired
	private AuthenticationManager mgr;
	@Autowired
	private UserDetailsService UserDetailsService;
	@Autowired
	private JwtUtil utils;

	public PaperSetterController() {
		System.out.println("In Constructor of " + getClass().getName());
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUpPaperSetter(@RequestBody @Valid PaperSetterDto dto, PaperSetter transientObj) {
		System.out.println("In signUpPaperSetter()");
		System.out.println(dto);
		transientObj = PaperSetterMapper.mapPaperSetterDtoToPapersetterEntity(dto, transientObj);
		System.out.println(transientObj);
		try {
			PaperSetter savedPaperSetter = service.savePaperSetter(transientObj);
			if (savedPaperSetter != null) {
				return new ResponseEntity<>(savedPaperSetter, HttpStatus.CREATED);
			} else {
				throw new IllegalArgumentException("Failed to Signup...");
			}
		} catch (RuntimeException e) {
			System.out.println("err in save " + e);
			throw new DataIntegrityViolationException("Email already exists in database Please try another mailId");
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
		System.out.println("In Login return phase");
		System.out.println(details);
		return new ResponseEntity<>(new AuthenticationResponse(utils.generateToken(details)), HttpStatus.OK);
		// return ResponseEntity.ok(new
		// AuthenticationResponse(utils.generateToken(details)),new PaperSetter());

	}

	@GetMapping
	public ResponseEntity<?> getPaperSetterId(HttpServletRequest req) {
		System.out.println("-----------------------------------------------------");
		System.out.println("In getPaperSetterId....");
		System.out.println("-----------------------------------------------------");
		String authHeader = req.getHeader("Authorization");
		String jwt = authHeader.substring(7);
		UserDetails user = UserDetailsService.loadUserByUsername(utils.extractUsername(jwt));
		PaperSetter paperSetter = service.getByEmail(user.getUsername());
		System.out.println("I Returning PapperSetterId  = "+paperSetter.getPaperSetterId());
//		return  new ResponseEntity<>;
		return new ResponseEntity<>(paperSetter.getPaperSetterId(), HttpStatus.OK);
	}

}
