package com.springboot.oauth.web.rest;

import com.springboot.oauth.UserService;
import com.springboot.oauth.domen.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello oauth2 hyona";
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(Principal principal){
        if (principal instanceof AbstractAuthenticationToken){
            return ResponseEntity.ok(userService.getUserFromAuthentication((AbstractAuthenticationToken) principal));
        }else {
            throw new IllegalArgumentException("Error");
        }
    }
}
