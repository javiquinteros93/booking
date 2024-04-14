package com.digitalbooking.demo.security.service;

import com.digitalbooking.demo.security.enums.RoleName;
import com.digitalbooking.demo.security.repository.RoleRepository;
import com.digitalbooking.demo.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface IRoleService {
    void createRole(Role role);
    Optional<Role> findByName(String roleName);
    Role addRole(Role role);
    List<Role> listRoles();
}
