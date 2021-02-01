package com.app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.repo.PaperSetterRepo;
import com.app.dao.repo.entity.PaperSetter;

@Service
@Transactional
public class DaoBasedUserDetailsService implements UserDetailsService {

	@Autowired
	private PaperSetterRepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("In loadUserByUsername() of class"+getClass().getName());
		 PaperSetter foundPaperSetter = repo.findByEmail(email);

		if (foundPaperSetter == null)
			throw new UsernameNotFoundException("User by name " + email + " not found!");
		return new CustomUserDetails(foundPaperSetter);
	}

}
