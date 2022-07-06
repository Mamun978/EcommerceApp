package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.model.WishList;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.AuthenticationService;
import com.ecommerce.service.WishListService;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    
    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;

    
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
     @RequestParam("token")String token){

        //authenticate the token
        authenticationService.authenticate(token);

        //find the user

        User user=authenticationService.getUser(token);

        //save product as wishlist item

        WishList wishList=new WishList( user, product);

        wishListService.createWishList(wishList);

        ApiResponse apiResponse=new ApiResponse(true,"Added to wishlist");

        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);

    }


    //get all wishlist item for a user


     
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token")String token){
  // authenticate the user
        this.authenticationService.authenticate(token);
// find the user
        User user=authenticationService.getUser(token);

        List<ProductDto> productDtos=wishListService.getWishListForUser(user);





        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }


}
