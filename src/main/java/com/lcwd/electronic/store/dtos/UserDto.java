package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // used for builder design pattern
public class UserDto {


        private String userId; // create coloumn in table

        @Size(min = 3,max = 20,message = "Invalid Name !!")
        private String name;

        //@Email(message = "Invalid User Email !!")
        @NotBlank(message = "Email is required")
        @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "Email is required")
        private String email;

        @NotBlank(message = "Password is required !!")
        private String password;

        @Size(min=4,max = 6,message = "Invalid gender")
        private String gender; // default length is varchar(255)

        @NotBlank(message = "Write something about yourself !!")
        private String about;

        @ImageNameValid // it will take custom message
        private String imageName;

// @Pattern - here we apply on the email, we want fully cutomise valid using @Pattern with regular expression
// Custom validator
}
