package com.coderszone.authentication.security;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.coderszone.authentication.model.User;
import com.coderszone.authentication.service.UserService;

 

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    
    public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private UserService userService;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
    	String username = auth.getName();
        String password = (String) auth.getCredentials();
 
        User user = userService.loadUserByUsername(username);
        System.out.println("=====================>"+username+":"+password);
        if (user == null) {
        	System.out.println("userName Not found");
            throw new BadCredentialsException("Username not found.");
        }
 
        if (!password.equals(user.getPassword())) {
        	System.out.println("Password Not found");
            throw new BadCredentialsException("Wrong password.");
        }
 
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
 
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()) ;
    }
 
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
