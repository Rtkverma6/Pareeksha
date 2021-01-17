package com.app.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.dao.entity.PaperSetter;

@SuppressWarnings("serial")
public class CustomUserDetails  implements UserDetails{
	PaperSetter paperSetter;
	
	public CustomUserDetails(PaperSetter paperSetter) {
		super();
		System.out.println("In Custructor of "+getClass().getName());
		System.out.println(paperSetter);
		this.paperSetter=paperSetter;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	public Long getId() {
		return paperSetter.getPaperSetterId();
	}

	@Override
	public String getPassword() {
		return paperSetter.getPassword();
	}

	@Override
	public String getUsername() {
		System.out.println("paperSetter.getEmail() ");
		return paperSetter.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [paperSetter=" + paperSetter + "]";
	}
	
	

}
