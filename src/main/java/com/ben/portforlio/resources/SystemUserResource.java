package com.ben.portforlio.resources;

import com.ben.portforlio.entities.Authentication;
import com.ben.portforlio.entities.SystemUser;
import com.ben.portforlio.repositories.AuthenticationRepository;
import com.ben.portforlio.repositories.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @author bkariuki
 */
@RestController
public class SystemUserResource {

    @Autowired
    protected SystemUserRepository userRepository;
    @Autowired
    protected AuthenticationRepository authenticationRepository;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @PostConstruct
    public void create() {
        SystemUser user = userRepository.findByEmail("administrator@gmail.com");
        if (Objects.isNull(user)) {

            user = new SystemUser();
            user.setFirstName("administrator");
            user.setLastName("pro");
            user.setEmail("administrator@gmail.com");
            user.setPhone("07888828828");
            user.setWorkGroupIds(null);
            userRepository.save(user);

            Authentication authentication = new Authentication();
            authentication.setUsername(user.getEmail());
            authentication.setPassword(passwordEncoder.encode("administrator_pro"));
            authentication.setUserId(user.getUserId());
//            authentication.setUserGroups(null);
            authenticationRepository.save(authentication);
        }
    }

    @GetMapping("/user-data")
    public Object data() {
        Object data = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return data;
    }
}
