package com.uuolf.service;

import com.uuolf.entity.User;
import com.uuolf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    public User loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository
                .findByEmail(email)
                .orElseThrow();
    }
}
