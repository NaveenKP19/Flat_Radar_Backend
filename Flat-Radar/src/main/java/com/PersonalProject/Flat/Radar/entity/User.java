package com.PersonalProject.Flat.Radar.entity;

import com.PersonalProject.Flat.Radar.enums.Gender;
import com.PersonalProject.Flat.Radar.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "firstName is required")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "last name is required")
    @Column(nullable = false, length = 50)
    private String lastName;

    @Email(message = "Invalid email format ")
    @NotBlank(message = "Email id required")
    @Column(nullable = false,unique = true)
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$",
                        message = "Invalid mobile number"
    )
    @Column(nullable = false,unique = true)
    private String mobileNumber;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be atleast 8 character")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @NotBlank(message = "Address is required")
    @Column(nullable = false, length = 400)
    private String street;

    @NotBlank(message = "city is required")
    @Column(nullable = false , length = 50)
    private String city;

    @NotBlank(message = "state is required")
    @Column(nullable = false)
    private String state;

    @NotBlank(message = "pincode is required")
   @Pattern(
           regexp = "^[1-9][0-9]{5}$",
           message = "Invalid Pincode"
   )
    @Column(nullable = false)
    private String pincode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false,unique = true)
    @NotBlank(message = "Aadhaar number is required")
    @Size(min = 12,max = 12, message = "Aadhaar number must be exactly 12 digits")
    @Pattern(
            regexp = "^\\d{12}$",
            message = "Aadhaar must be of 12 digit"
    )
    private String aadhaarNumber;

    private boolean active = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deleted = false;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = LocalDateTime.now();
    }




}
