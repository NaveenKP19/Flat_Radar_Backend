package com.PersonalProject.Flat.Radar.mapper;

import com.PersonalProject.Flat.Radar.dto.RegisterRequestDto;
import com.PersonalProject.Flat.Radar.dto.UserResponsDto;
import com.PersonalProject.Flat.Radar.entity.User;

public class UserMapper {

    public static User toEntity(RegisterRequestDto dto){

        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .mobileNumber(dto.getMobileNumber())
                .email(dto.getEmail())
                .city(dto.getCity())
                .aadhaarNumber(dto.getAadhaarNumber())
                .role(dto.getRole())
                .state(dto.getState())
                .password(dto.getPassword())
                .pincode(dto.getPincode())
                .street(dto.getStreet())
                .gender(dto.getGender())
                .build();
    }

    public static UserResponsDto toResponseDto(User user){

        UserResponsDto dto  = new UserResponsDto();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setGender(user.getGender());
        dto.setCity(user.getCity());
        dto.setState(user.getState());
        dto.setStreet(user.getStreet());

        return dto;
    }
}
