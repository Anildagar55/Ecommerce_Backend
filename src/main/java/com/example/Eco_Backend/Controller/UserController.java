package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.UserResponse;
import com.example.Eco_Backend.DTO.UserSignupRequest;
import com.example.Eco_Backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<UserResponse>signup( @RequestBody UserSignupRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
