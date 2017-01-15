package com.coderszone.authentication.model;

import java.util.Collection;
import java.util.List;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;
 
    private int id;
    private String username;
    private String password;
    private String name;
    private String verificationKey;
    private int isVerified;
 
    /* Spring Security fields*/
    private Collection<GrantedAuthority> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
 
    public User(String returnUsername, String password2,String name,Collection<GrantedAuthority> authorities) {
		this.username=returnUsername;
		this.password=password2;
		this.name=name;
		this.authorities=authorities;
		this.accountNonExpired=true;
		this.accountNonLocked=true;
		this.credentialsNonExpired=true;
		this.enabled=true;
		
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
     
    @Override
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
 
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
     
    public void setAuthorities(Collection<GrantedAuthority> roles) {
        this.authorities = roles;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
     
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
     
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }
     
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
 
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
 
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [username=");
        builder.append(username);
        builder.append(", password=");
        builder.append(password);
        builder.append(", firstName=");
        builder.append(name);
        builder.append(", authorities=");
        builder.append(authorities);
        builder.append(", accountNonExpired=");
        builder.append(accountNonExpired);
        builder.append(", accountNonLocked=");
        builder.append(accountNonLocked);
        builder.append(", credentialsNonExpired=");
        builder.append(credentialsNonExpired);
        builder.append(", enabled=");
        builder.append(enabled);
        builder.append("]");
        return builder.toString();
    }

	public String getVerificationKey() {
		return verificationKey;
	}

	public void setVerificationKey(String verificationKey) {
		this.verificationKey = verificationKey;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}