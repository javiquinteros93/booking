package com.digitalbooking.demo.security.service;

import com.digitalbooking.demo.security.DTO.UserDTO;
import com.digitalbooking.demo.security.model.User;
import com.digitalbooking.demo.security.model.MainUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {
    void createUser(User user);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    UserDTO getUserCurrent(MainUser userCurrent);
    List<User> listUsers();
    User addUser(User user);
    Optional<User> findById(Long id);
}
