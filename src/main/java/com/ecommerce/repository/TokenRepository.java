package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;
@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer>  {
    
    // Token has saved in the database according to an user
    // we can find user by token
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);

}
