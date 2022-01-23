package com.sanvalero.AAMaria_Arruda.repository;

import com.sanvalero.AAMaria_Arruda.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
