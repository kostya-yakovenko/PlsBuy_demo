package com.web.PLSbuy.services;


import com.web.PLSbuy.models.User;
import com.web.PLSbuy.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Trying to find user with email: " + email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User not found!");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User found: " + user.getEmail());
        System.out.println("Roles: " + user.getRoles());
        System.out.println("Authorities: " + user.getAuthorities());
        return user;
    }

}
