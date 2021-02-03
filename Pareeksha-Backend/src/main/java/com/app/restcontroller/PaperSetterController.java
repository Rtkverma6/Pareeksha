package com.app.restcontroller;

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

import com.app.dao.entity.PaperSetter;
import com.app.dto.AuthenticationRequest;
import com.app.dto.AuthenticationResponse;
import com.app.dto.PaperSetterdto;
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
	public ResponseEntity<?> signUpPaperSetter(@RequestBody @Valid PaperSetterdto dto, PaperSetter transientObj) {
		System.out.println("In signUpPaperSetter()");
		return new ResponseEntity<> (service.savePaperSetter(dto, transientObj),HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> createJwtToken(@RequestBody AuthenticationRequest req) {
		try {
			mgr.authenticate(new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword()));
			System.out.println("authenticate successfull");
		} catch (BadCredentialsException e) {
			throw new RuntimeException("Invalid UserName or password");
		}
		
		UserDetails details = UserDetailsService.loadUserByUsername(req.getUserName());
		System.out.println("In Login return phase");
		System.out.println(details);
		return new ResponseEntity<>(new AuthenticationResponse(utils.generateToken(details)), HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<?> getPaperSetterId(HttpServletRequest req) {
		System.out.println("In getPaperSetterId....");

		String authHeader = req.getHeader("Authorization");
		String jwt = authHeader.substring(7);
		UserDetails user = UserDetailsService.loadUserByUsername(utils.extractUsername(jwt));
		PaperSetter paperSetter = service.getByEmail(user.getUsername());
		System.out.println("In Returning PapperSetterId  = " + paperSetter.getPaperSetterId());
		return new ResponseEntity<>(paperSetter.getPaperSetterId(), HttpStatus.OK);
	}

}
