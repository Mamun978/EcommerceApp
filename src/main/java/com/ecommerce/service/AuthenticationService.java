package com.ecommerce.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.AuthenticationFailException;
import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;
import com.ecommerce.repository.TokenRepository;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
       this.tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user){
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token){
      final  AuthenticationToken authenticationToken=tokenRepository.findByToken(token);
      if(Objects.isNull(authenticationToken)){
        return null;
        
      }
      // authentication token is not null
      return authenticationToken.getUser();
    }
    public void authenticate(String token){
        //null check

        if(Objects.isNull(token)){
            //throw exception
            //passing null
            throw new AuthenticationFailException("Token not found");
        }
        if(Objects.isNull(getUser(token))){
            //User with that particular token not found
            throw new AuthenticationFailException("Not valid token");
        }

       

    }
    
}
