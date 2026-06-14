package com.PersonalProject.Flat.Radar.service.impl;

import com.PersonalProject.Flat.Radar.dto.LoginRequestDto;
import com.PersonalProject.Flat.Radar.dto.LoginResponseDto;
import com.PersonalProject.Flat.Radar.dto.RegisterRequestDto;
import com.PersonalProject.Flat.Radar.dto.UserResponsDto;
import com.PersonalProject.Flat.Radar.entity.User;
import com.PersonalProject.Flat.Radar.exception.*;
import com.PersonalProject.Flat.Radar.mapper.UserMapper;
import com.PersonalProject.Flat.Radar.repository.UserRepository;
import com.PersonalProject.Flat.Radar.security.JwtUtil;
import com.PersonalProject.Flat.Radar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserResponsDto registerUser(RegisterRequestDto dto) {
        System.out.println("========== SERVICE HIT ==========");
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException("Email Already Registered");
        }

        if(userRepository.existsByMobileNumber(dto.getMobileNumber())){
            throw new MobileNumberAlreadyExistsException("Mobile number already registered");
        }

        if(userRepository.existsByAadhaarNumber(dto.getAadhaarNumber())){
            throw new AadhaarAlreadyExistsException("Aadhaar already registered");
        }

        User user = UserMapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        return UserMapper.toResponseDto(savedUser);


    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto dto) {

    User user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(()-> new UserNotFoundException("User Not Found"));

    if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
        throw new InvalidPasswordException("Invalid Password");
    }

    String token =jwtUtil.generateToken(user.getEmail());

    return new LoginResponseDto(token,"Login Successfully");
    }

    @Override
    public UserResponsDto getProfile(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("USer Not Found"));
        return  UserMapper.toResponseDto(user);
    }
}
