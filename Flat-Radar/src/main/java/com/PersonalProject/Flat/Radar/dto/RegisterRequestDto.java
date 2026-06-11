package com.PersonalProject.Flat.Radar.dto;

import com.PersonalProject.Flat.Radar.enums.Gender;
import com.PersonalProject.Flat.Radar.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "First Name is required ")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Format is invalid")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String mobileNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be minimum 8 character")
    private String password;

    private Role role;

    private Gender gender;

    private String city;

    private String state;

    private String pincode;

    private String aadhaarNumber;

    private String street;

}
