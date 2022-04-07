package com.example.mangxahoi.service;

import com.example.mangxahoi.model.Role;
import com.example.mangxahoi.model.RoleName;


import java.util.Optional;

public interface IRoleService{
    Optional<Role> findByName(RoleName name);
}
