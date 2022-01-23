package com.sanvalero.AAMaria_Arruda.service.impl;

import com.sanvalero.AAMaria_Arruda.domain.Role;
import com.sanvalero.AAMaria_Arruda.domain.User;
import com.sanvalero.AAMaria_Arruda.repository.RoleRepository;
import com.sanvalero.AAMaria_Arruda.repository.UserRepository;
import com.sanvalero.AAMaria_Arruda.security.Constants;
import com.sanvalero.AAMaria_Arruda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean add(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreationDate(LocalDate.now());
        user.setActive(true);
        Role userRole = roleRepository.findByName(Constants.USER_ROLE);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);

        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public Set<User> findAll() {
        return null;
    }


    @Override
    public Set<User> findByCity(String username) {
        return null;
    }


}
