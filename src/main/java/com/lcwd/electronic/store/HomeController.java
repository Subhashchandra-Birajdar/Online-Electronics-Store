package com.lcwd.electronic.store;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {

    @GetMapping
    public String testing(){
        return "Welcome to electronic store";
    }
}
