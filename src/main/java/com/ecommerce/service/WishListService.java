package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.User;
import com.ecommerce.model.WishList;
import com.ecommerce.repository.WishListRepository;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductService productService;
    
     

    public void createWishList(WishList wishList) {
        this.wishListRepository.save(wishList);
    }



    public List<ProductDto> getWishListForUser(User user) {

       final List<WishList> wishLists = this.wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        
        List<ProductDto>productDtos=new ArrayList<>();

        for(WishList wisList: wishLists){
            productDtos.add(productService.getProductDto(wisList.getProduct()));
        }

        return productDtos;
    }


}
