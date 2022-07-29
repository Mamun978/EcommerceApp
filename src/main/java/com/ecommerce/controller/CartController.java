package com.ecommerce.controller;

import com.ecommerce.dto.Cart.AddToCartDto;
import com.ecommerce.dto.Cart.CartDto;
import com.ecommerce.model.User;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.AuthenticationService;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    //post cart api
     @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token){

        //authenticate the token
        authenticationService.authenticate(token);

        //find the user


        User user=authenticationService.getUser(token);

        cartService.addToCart(addToCartDto,user);

        return  new ResponseEntity<>(new ApiResponse(true,"Added to cart"), HttpStatus.CREATED);



    }

    // get all cart items for a user

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token")String token){

        //authenticate the token
        authenticationService.authenticate(token);

        //find the user

        User user=authenticationService.getUser(token);

        //get cart items
        CartDto cardDto=cartService.getListOfCartItems(user);

        return  new ResponseEntity<>(cardDto, HttpStatus.OK);
    }

    // delete a cart for a user


    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId")Integer cartItemId,
                                                      @RequestParam("token")String token){

        //authenticate the token
        authenticationService.authenticate(token);

        //find the user

        User user=authenticationService.getUser(token);

        cartService.deleteCart(cartItemId,user);

        return  new ResponseEntity<>(new ApiResponse(true,"Cart Item deleted"), HttpStatus.OK);
    }
}
