package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.UserResponse;
import com.example.Eco_Backend.DTO.UserSignupRequest;
import com.example.Eco_Backend.Entity.User;
import com.example.Eco_Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

   private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserResponse signup(UserSignupRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalStateException("Email already register : "+request.getEmail());
        }
        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .created_at(LocalDateTime.now())
                .password_hash(passwordEncoder.encode(request.getPassword()))
                .build();
     return mapToResponse(userRepository.save(user));
    }
    public UserResponse getUserById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found : "+id));
          return mapToResponse(user);
    }
    private UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
