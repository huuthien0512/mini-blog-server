package com.miniblogserver.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.miniblogserver.model.User;

public class Util {
	
	private Util() {}
	
	public static User getCurrentUser() {
		return (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
	}
}
