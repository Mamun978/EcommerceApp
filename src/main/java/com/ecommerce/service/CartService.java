package com.ecommerce.service;

import com.ecommerce.dto.Cart.AddToCartDto;
import com.ecommerce.dto.Cart.CartDto;
import com.ecommerce.dto.Cart.CartItemDto;
import com.ecommerce.exceptions.CustomException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private  ProductService productService;

    @Autowired
    CartRepository cartRepository;


    public void addToCart(AddToCartDto addToCartDto, User user) {

        //validate the product id

       Product product= productService.findById(addToCartDto.getProductId());

       Cart cart=new Cart();

       cart.setProduct(product);
       cart.setUser(user);
       cart.setCreatedDate(new Date());
       cart.setQuantity(addToCartDto.getQuantity());
        //save the cart
       cartRepository.save(cart);





    }

    public CartDto getListOfCartItems(User user) {
      List<Cart> cartList=  cartRepository.findAllByUserOrderByCreatedDateDesc(user);

      //we have to change cart list to cartDto

        List<CartItemDto> cartItem=new ArrayList<>();
        double totalCost=0;
        for(Cart cart:cartList){
            CartItemDto cartItemDto= new CartItemDto(cart);
            totalCost+=cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItem.add(cartItemDto);
        }
        CartDto cartDto=new CartDto();
        cartDto.setTotalCost(totalCost);;
        cartDto.setCartItem(cartItem);

        return  cartDto;
    }

    public void deleteCart(Integer cartItemId, User user) {

        Optional<Cart>optionalCart= cartRepository.findById(cartItemId);

        if(optionalCart.isEmpty()){
            throw  new CustomException("cart is empty");
        }

        Cart cart=optionalCart.get();

        if(cart.getUser()!=user){
            throw  new CustomException("Cart related to the user is not found");
        }

        this.cartRepository.delete(cart);;
    }
}
