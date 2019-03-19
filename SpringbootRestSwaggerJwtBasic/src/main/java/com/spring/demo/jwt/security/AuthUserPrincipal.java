package com.spring.demo.jwt.security;

import com.spring.demo.entity.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class AuthUserPrincipal implements UserDetails {
	
	private Customer customer;

    public AuthUserPrincipal(Customer customer) {
        this.customer = customer;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (customer.getSuperAdmin() !=null && customer.getSuperAdmin()) {
			authList.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
		} else if (customer.getAdmin() != null && customer.getAdmin()) {
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
        return authList;
	}

	@Override
	public String getPassword() {
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		return customer.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

    public Long getId() {
    	return customer.getId();
    }
    
    public String getFullName() {
    	return customer.getFullName();
    }
    
    public Customer getCustomer() {
    	return customer;
    }

}
