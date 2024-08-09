package com.lcwd.electronic.store.controllers;

import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.payloads.ApiResponseMessage;
import com.lcwd.electronic.store.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}") // in url we are passing dynamic data thats why we are using pathvariable & userId
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId")String userId){
        return new ResponseEntity<>(userService.updateUser(userDto,userId),HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId")String userId){
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder().message("User deleted successfully !!!").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    // get all
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

    //get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId")String userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    //get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email")String userEmail){
        return new ResponseEntity<>(userService.getUserByEmail(userEmail),HttpStatus.OK);
    }

    // search user
    @GetMapping("/search-by-name/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("keywords")String keywords){
        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
    }
}
