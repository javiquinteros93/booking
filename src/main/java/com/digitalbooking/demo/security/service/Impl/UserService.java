package com.digitalbooking.demo.security.service.Impl;

import com.digitalbooking.demo.security.DTO.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digitalbooking.demo.security.model.MainUser;
import com.digitalbooking.demo.security.model.User;
import com.digitalbooking.demo.security.repository.UserRepository;
import com.digitalbooking.demo.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTO getUserCurrent(MainUser userCurrent) {
        return new UserDTO(userCurrent.getId(), userCurrent.getName(),
                userCurrent.getSurname(), userCurrent.getEmail(), userCurrent.getAuthorities());
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }


}