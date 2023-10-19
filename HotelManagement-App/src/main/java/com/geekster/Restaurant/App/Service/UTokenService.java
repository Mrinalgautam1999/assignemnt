package com.geekster.Restaurant.App.Service;

import com.geekster.Restaurant.App.Model.AdminTokens;
import com.geekster.Restaurant.App.Model.DTO.UserAuth;
import com.geekster.Restaurant.App.Model.UserToken;
import com.geekster.Restaurant.App.Model.Users.User;
import com.geekster.Restaurant.App.Repo.IUserTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UTokenService {

    @Autowired
    IUserTokenRepo iUserTokenRepo;

    public void deleteToken(String tokenValue) {
        UserToken token = iUserTokenRepo.findFirstByTokenValue(tokenValue);
        iUserTokenRepo.delete(token);
    }

    public boolean authenticate(UserAuth userAuth) {
        String email = userAuth.getUserEmail();
        String tokenValue = userAuth.getTokenValue();

        UserToken token = iUserTokenRepo.findFirstByTokenValue(tokenValue);
        if(token != null){
            return token.getUser().getUserEmail().equals(email);
        }else{
            return false;
        }

    }

    public void createToken(UserToken userToken) {
        iUserTokenRepo.save(userToken);
    }
}
