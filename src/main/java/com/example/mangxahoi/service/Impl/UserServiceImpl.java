package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.model.User;
import com.example.mangxahoi.repository.IUserRepo;
import com.example.mangxahoi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepo userRepo;
    @Override
    public Optional<User> findByUsername(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public List<User> findUserByUsername(String name) {
        return userRepo.findUserByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public List<User> findUserByRole(Long id) {
        return userRepo.findUserByRole(id);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByID(Long id) {
        return userRepo.findById(id).get();
    }

}
