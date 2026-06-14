package com.PersonalProject.Flat.Radar.controller;

import com.PersonalProject.Flat.Radar.dto.LoginRequestDto;
import com.PersonalProject.Flat.Radar.dto.LoginResponseDto;
import com.PersonalProject.Flat.Radar.dto.RegisterRequestDto;
import com.PersonalProject.Flat.Radar.dto.UserResponsDto;
import com.PersonalProject.Flat.Radar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

@PostMapping("/register")
  public ResponseEntity<UserResponsDto> registerUser(@Valid @RequestBody RegisterRequestDto dto){
        UserResponsDto response =  userService.registerUser(dto);
    System.out.println("========== CONTROLLER HIT ==========");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequestDto dto){

       LoginResponseDto response = userService.loginUser(dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "Working";
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponsDto> profile(Authentication authentication){

    String email = authentication.getName();

    UserResponsDto response = userService.getProfile(email);
    return ResponseEntity.ok(response);
    }
}

