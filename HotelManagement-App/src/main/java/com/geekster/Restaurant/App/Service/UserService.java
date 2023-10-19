package com.geekster.Restaurant.App.Service;

import com.geekster.Restaurant.App.Model.AdminTokens;
import com.geekster.Restaurant.App.Model.DTO.UserAuth;
import com.geekster.Restaurant.App.Model.DTO.UserDto;
import com.geekster.Restaurant.App.Model.Orders;
import com.geekster.Restaurant.App.Model.UserToken;
import com.geekster.Restaurant.App.Model.Users.Admin;
import com.geekster.Restaurant.App.Model.Users.User;
import com.geekster.Restaurant.App.Repo.IUserRepo;
import com.geekster.Restaurant.App.Service.EmailHandler.EmailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    UTokenService uTokenService;

    public String userSignUp(User user) {
        String email = user.getUserEmail();
        User existingEmail = userRepo.findFirstByUserEmail(email);
        if(existingEmail != null){
            return "This Email is exiting Email, please fill the password to login!!!";
        }


        String password = user.getUserPassword();
        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            return "Admin registered";
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignIn(UserDto userDto) {
        String email = userDto.getUserEmail();

        //find the user
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser == null){
            return "Email is not registered. Sign up First!!!";
        }
        //checkPassword
        String password = userDto.getUserPassword();

        try {
            String passwordEncryptor = PasswordEncryptor.encrypt(password);
            if(existingUser.getUserPassword().equals(passwordEncryptor)){
                UserToken userTokens = new UserToken(existingUser);
                if(EmailHandler.sendEmail(email,"otp after login", userTokens.getTokenValue())) {
                    uTokenService.createToken(userTokens);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }
            }else{
                return "Invalid credential!!!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Authentication Invalid!!!";
        }
    }

    public String userSignOut(UserAuth userAuth) {
        if(uTokenService.authenticate(userAuth)) {
            String tokenValue = userAuth.getTokenValue();
            uTokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }


}