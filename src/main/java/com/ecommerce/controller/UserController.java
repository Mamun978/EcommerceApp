package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ResponseDto;
import com.ecommerce.dto.user.SignupDto;
import com.ecommerce.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {
     
   @Autowired
   private UserService userService;

   @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return userService.signUp(signupDto);

        //save the password

        //hash the password
        //create the token
    }
}
