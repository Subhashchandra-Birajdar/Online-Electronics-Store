package com.lcwd.electronic.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

        @Email(message = "Invalid User Email !!")
        private String email;

        @NotBlank(message = "Password is required !!")
        private String password;

        @Size(min=4,max = 6,message = "Invalid gender")
        private String gender; // default length is varchar(255)

        @NotBlank(message = "Write something about yourself !!")
        private String about;
        private String imageName;

// @Pattern
// Custom validator
}
