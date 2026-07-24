package com.example.Eco_Backend.Repository;

import com.example.Eco_Backend.DTO.UserSignupRequest;
import com.example.Eco_Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
public boolean existsByEmail(UserSignupRequest request);
}
