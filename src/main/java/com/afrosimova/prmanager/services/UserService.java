package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.repositories.UserRepository;
import com.afrosimova.prmanager.entities.User;
import com.afrosimova.prmanager.security.MyUserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUser(username);
        if (user.size() != 1) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserPrincipal(user.get(0));
    }
}
