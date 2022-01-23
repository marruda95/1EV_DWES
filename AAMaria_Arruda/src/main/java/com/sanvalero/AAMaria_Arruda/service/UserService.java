package com.sanvalero.AAMaria_Arruda.service;
import com.sanvalero.AAMaria_Arruda.domain.User;
import java.util.Set;

/** SERVICIO PARA GESTIÓN DE USUARIOS **/

public interface UserService {
    boolean add(User user);
    void remove(User user);
    Set<User> findAll();
    Set<User> findByCity(String username);
    User findByUsername(String username);
}
