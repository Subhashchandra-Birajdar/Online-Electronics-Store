package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;

import java.io.IOException;
import java.util.List;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    // delete
    void deleteUser(String userId) throws IOException;

    // get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sorBy, String sortDir);

    // get single user by id
    UserDto getUserById(String userId);

    // get single user by email
    UserDto getUserByEmail(String email);

    // search user
    List<UserDto> searchUser(String keyword);

    // other user specific  features
}

/*
Flow
--
Frontend request to -> controller -> Dto object ->transfer to -> service ->Logic,Dto to entity,->data layer(repository) -> entity -> table
// we can't expose entity in controller, because entity is mapped to database
//entity represent data
//dto is an design pattern

//organise code in intellij use ctr+l
*/