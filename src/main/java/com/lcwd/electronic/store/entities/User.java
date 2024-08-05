package com.lcwd.electronic.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // used for builder design pattern
@Entity // when u add entity, it class represent as table user
@Table(name = "users") // here customise table name
public class User {

    @Id // make id as primary key, in entity mandatory we add id
    //@GeneratedValue(strategy=GeneratedType.IDENTITY) // for mysql is must
    private String UserId; // create coloumn in table

    @Column(name = "user_name") //customise column name
    private String name;

    @Column(name = "user_email", unique = true) // be default unique is false
    private String email;

    private String gender; // default length is varchar(255)

    @Column(name = "user_image_name") // only name is stored in db, images stores in image
    private String imageName;

    @Column(length = 1000)
    private String about;
}

