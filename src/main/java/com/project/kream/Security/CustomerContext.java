package com.project.kream.Security;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.enumclass.CustomerRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomerContext extends User {
    private Customer customer;

    public CustomerContext(Customer customer){
        super(customer.getEmail(), customer.getUserpw(), getAuthorities(customer.getRole()));
        this.customer = customer;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(CustomerRole role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getKey()));
    }
}
