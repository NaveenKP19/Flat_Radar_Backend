package com.PersonalProject.Flat.Radar.dto;

import com.PersonalProject.Flat.Radar.enums.Gender;
import com.PersonalProject.Flat.Radar.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponsDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private Role role;

    private Gender gender;

    private String city;

    private String state;

    private  String street;
}
