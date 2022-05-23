package com.example.mangxahoi.controller;

import com.example.mangxahoi.dto.request.SignInForm;
import com.example.mangxahoi.dto.request.SignUpForm;
import com.example.mangxahoi.dto.respon.JwtResponse;
import com.example.mangxahoi.dto.respon.ResponseMess;
import com.example.mangxahoi.model.Role;
import com.example.mangxahoi.model.RoleName;
import com.example.mangxahoi.model.User;
import com.example.mangxahoi.security.jwt.JwtProvider;
import com.example.mangxahoi.security.userprincal.UserPrinciple;
import com.example.mangxahoi.service.Impl.RoleServiceImpl;
import com.example.mangxahoi.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginCtrl {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    AuthenticationManager auth;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMess("The username existed! Please try again!"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMess("The email existed! Please try again!"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
        } //tạo avatar mặc định
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found!")
                    );
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.ROLE_PM).orElseThrow(
                            () -> new RuntimeException(" Role manager not found")
                    );
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.ROLE_USER).orElseThrow(
                            () -> new RuntimeException(" Role user not found")
                    );
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMess("Sign Up Success!"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login (@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        System.out.println("userPrinciple");
        System.out.println(userPrinciple);
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
//        trả kèm theo id;
        return ResponseEntity.ok( new JwtResponse(userPrinciple.getId(), token, userPrinciple.getName(), userPrinciple.getAvatar()  , userPrinciple.getAuthorities()));
//        return ResponseEntity.ok( new JwtResponse(token, user));
    }
}
