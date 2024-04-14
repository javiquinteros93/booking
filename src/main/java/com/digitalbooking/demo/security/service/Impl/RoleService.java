package com.digitalbooking.demo.security.service.Impl;

import com.digitalbooking.demo.security.enums.RoleName;
import com.digitalbooking.demo.security.model.Role;
import com.digitalbooking.demo.security.repository.RoleRepository;
import com.digitalbooking.demo.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class RoleService implements IRoleService{

    @Autowired
    RoleRepository roleRepository;

    public void createRole(Role role){
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findByName(String rolName) {
        return roleRepository.findByName(rolName);
    }

    @Override
    public Role addRole(Role role) {
        return null;
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }
}