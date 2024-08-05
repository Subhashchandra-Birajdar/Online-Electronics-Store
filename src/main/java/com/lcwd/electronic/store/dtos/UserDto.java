package com.lcwd.electronic.store.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // used for builder design pattern
public class UserDto {


        private String UserId; // create coloumn in table

        private String name;

        private String email;

        private String gender; // default length is varchar(255)

        private String imageName;

        private String about;
}