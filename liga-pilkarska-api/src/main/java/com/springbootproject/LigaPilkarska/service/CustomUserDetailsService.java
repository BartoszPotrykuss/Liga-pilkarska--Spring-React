package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.CustomUserDetails;
import com.springbootproject.LigaPilkarska.entity.User;
import com.springbootproject.LigaPilkarska.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }
        return new CustomUserDetails(user);
    }
}
