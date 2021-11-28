package com.project.kream.Security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FormAuthenticationProvider implements AuthenticationProvider {
    private CustomUserDetailsService customUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String userpw = (String)authentication.getCredentials();

        CustomerContext customerContext = (CustomerContext) customUserDetailsService.loadUserByUsername(email);
        String password = customerContext.getCustomer().getUserpw();

        if(!passwordEncoder.matches(userpw, password)){
            throw new BadCredentialsException("비밀번호가 틀립니다");
        }
        return new UsernamePasswordAuthenticationToken(customerContext.getCustomer(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
