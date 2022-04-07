package com.example.mangxahoi.service;

import com.example.mangxahoi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String name); // tim kiem user co ton tai ko
    List<User> findUserByUsername(String name);// tim kiem list user có tên gần giống
    Boolean existsByUsername(String username);// kiem tra co ton tai hay ko
    Boolean existsByEmail(String email);// kiem tra email
    List<User> findUserByRole(Long id);
    User save(User user);
    void delete(Long id);
    List<User> findAll();
    User findUserByID(Long id);

}
