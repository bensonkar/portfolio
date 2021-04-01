package com.ben.portforlio.services;

import com.ben.portforlio.repositories.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author bkariuki
 */
@Service
public class UsernameService {
    @Autowired
    protected AuthenticationRepository authenticationRepository;

    public String getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

    public Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        System.out.println("LOGGED IN USER : " + username);
        return authenticationRepository.findByUsername(username).getUserId();
    }
}
