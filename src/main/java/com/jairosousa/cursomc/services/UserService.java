package com.jairosousa.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jairosousa.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {

		try {
			System.out.println("UserSS"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} 
		catch (Exception e) {
			return null;
		}
	}
}

