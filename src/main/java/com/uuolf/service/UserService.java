package com.uuolf.service;

import com.uuolf.entity.User;
import com.uuolf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User userToRegister) {
        if (userRepository.existsById(userToRegister.getId())) {
            throw new RuntimeException("Usuário já existe.");
        }
        return userRepository.save(userToRegister);
    }
}
