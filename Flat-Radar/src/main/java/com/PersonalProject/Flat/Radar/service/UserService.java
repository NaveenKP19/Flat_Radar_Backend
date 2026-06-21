package com.PersonalProject.Flat.Radar.service;
import com.PersonalProject.Flat.Radar.dto.LoginRequestDto;
import com.PersonalProject.Flat.Radar.dto.LoginResponseDto;
import com.PersonalProject.Flat.Radar.dto.RegisterRequestDto;
import com.PersonalProject.Flat.Radar.dto.UserResponsDto;
public interface UserService {

    UserResponsDto registerUser(RegisterRequestDto dto);
    LoginResponseDto loginUser(LoginRequestDto dto);
    UserResponsDto getProfile(String email);
}
