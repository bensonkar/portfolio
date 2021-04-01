package com.ben.portforlio.services;

import com.ben.portforlio.entities.Authentication;
import com.ben.portforlio.repositories.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author bkariuki
 */
@Service("userDetails")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    protected AuthenticationRepository authenticationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Authentication user = authenticationRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("user not found with this name " + username);
        return user;
    }
}
