package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.model.Role;
import com.example.mangxahoi.model.RoleName;
import com.example.mangxahoi.repository.IRoleRepo;
import com.example.mangxahoi.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepo roleRepo;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepo.findByName(name);
    }
}
