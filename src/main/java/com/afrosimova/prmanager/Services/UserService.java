package com.afrosimova.prmanager.Services;

import com.afrosimova.prmanager.Repository.UserRepository;
import com.afrosimova.prmanager.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }
}
