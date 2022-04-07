package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Role;
import com.example.mangxahoi.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
