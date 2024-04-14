package com.digitalbooking.demo.security.controller;

import com.digitalbooking.demo.security.DTO.JwtDTO;
import com.digitalbooking.demo.security.DTO.LoginUserDTO;
import com.digitalbooking.demo.security.DTO.RegisterUserDTO;

import com.digitalbooking.demo.security.JWT.JwtTokenFilter;
import com.digitalbooking.demo.security.enums.RoleName;
import com.digitalbooking.demo.security.JWT.JwtProvider;
import com.digitalbooking.demo.security.model.MainUser;
import com.digitalbooking.demo.security.model.Role;
import com.digitalbooking.demo.security.model.User;
import com.digitalbooking.demo.security.service.IRoleService;
import com.digitalbooking.demo.security.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    IUserService iUserService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO){
        if(iUserService.existsByEmail(registerUserDTO.getEmail())){
            return ResponseEntity.ok("Email already exists");
        }
        User user = new User(registerUserDTO.getName(), registerUserDTO.getSurname(), registerUserDTO.getEmail(), passwordEncoder.encode(registerUserDTO.getPassword()));
        Role role = iRoleService.findByName(RoleName.client.toString()).get();
        if(registerUserDTO.getRole().equals("admin"))
            role = iRoleService.findByName(RoleName.admin.toString()).get();
        user.setRole(role);
        iUserService.createUser(user);
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(registerUserDTO.getEmail(),
                registerUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        MainUser userDetails = (MainUser) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(userDetails.getId(), userDetails.getName(), userDetails.getSurname(), jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO, HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping(path = "/login")
    public ResponseEntity<JwtDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO){
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(),
                loginUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        MainUser userDetails = (MainUser) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(userDetails.getId(), userDetails.getName(), userDetails.getSurname(), jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/token")
    public boolean checkToken(HttpServletRequest request) {
        String token = jwtTokenFilter.getToken(request);
        return jwtProvider.validateToken(token);
    }

}
