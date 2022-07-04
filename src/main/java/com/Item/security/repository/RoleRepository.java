package com.Item.security.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Item.security.models.ERole;
import com.Item.security.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
Optional<Role> findByName(ERole name);
}
