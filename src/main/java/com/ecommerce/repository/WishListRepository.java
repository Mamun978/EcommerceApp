package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.User;
import com.ecommerce.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
    
}
