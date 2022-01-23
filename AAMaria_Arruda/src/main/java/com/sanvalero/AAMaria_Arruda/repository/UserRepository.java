package com.sanvalero.AAMaria_Arruda.repository;

import com.sanvalero.AAMaria_Arruda.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    Set<User> findByCity(String city);
    Set<User> findByPostalCode(String postalCode);
}
