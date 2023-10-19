package com.geekster.Restaurant.App.Service;

import com.geekster.Restaurant.App.Model.DTO.AdminDto;
import com.geekster.Restaurant.App.Model.DTO.AdminAuth;
import com.geekster.Restaurant.App.Model.AdminTokens;
import com.geekster.Restaurant.App.Model.DTO.ChangePasswordDto;
import com.geekster.Restaurant.App.Model.Users.Admin;
import com.geekster.Restaurant.App.Repo.IAdminRepo;
import com.geekster.Restaurant.App.Repo.ITokenRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {


    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    ITokenRepo tokenRepo;

    @Autowired
    ATokenService ATokenService;

    public String adminSignUp(Admin admin) {

        //check email -> if existing return it
        String email = admin.getAdminEmail();
        Admin existingEmail = adminRepo.findByAdminEmail(email);
        if(existingEmail != null){
            return "This Email is exiting Email, please fill the password to login!!!";
        }


        String password = admin.getPassword();
        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            admin.setPassword(encryptedPassword);
            adminRepo.save(admin);
            return "Admin registered";
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String adminSignIn(AdminDto adminDetail) {

        //get email of admin
        String email = adminDetail.getAdminEmail();

        //find the admin
        Admin existingAdmin = adminRepo.findByAdminEmail(email);
        if(existingAdmin == null){
            return "Email is not registered. Sign up First!!!";
        }
        //checkPassword
        String password = adminDetail.getPassword();

        try {
            String passwordEncryptor = PasswordEncryptor.encrypt(password);
            if(existingAdmin.getPassword().equals(passwordEncryptor)){
                AdminTokens adminTokens = new AdminTokens(existingAdmin);
                ATokenService.createToken(adminTokens);
                return adminTokens.getTokenValue();
            }else{
                return "Invalid credential!!!";
            }
        } catch (NoSuchAlgorithmException e) {

            return "Authentication Invalid!!!";
        }
    }

    public String adminSignOut(AdminAuth adminAuth) {

        if(ATokenService.authenticate(adminAuth)) {
            String tokenValue = adminAuth.getTokenValue();
            ATokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String forgetPassword(String adminEmail) {


        //email -> token -> token value and delete
        Admin existingAdmin = adminRepo.findByAdminEmail(adminEmail);
        if (existingAdmin != null) {
            AdminTokens adminTokens1 = tokenRepo.findByAdmin(existingAdmin);
            if (adminTokens1 != null) {
                tokenRepo.delete(adminTokens1);
            }
            AdminTokens adminTokens = new AdminTokens(existingAdmin);
            ATokenService.createToken(adminTokens);

            return adminTokens.getTokenValue();
        } else {
            return "the admin with the given email doesn't exist";
        }
    }

    public String updatePassword(ChangePasswordDto changePasswordDto) {

        String tokenValue = changePasswordDto.getTokenValue();
        AdminTokens token = tokenRepo.findByTokenValue(tokenValue);
        String email = token.getAdmin().getAdminEmail();

        Admin admin = adminRepo.findByAdminEmail(email);
        try {
            admin.setPassword(PasswordEncryptor.encrypt(changePasswordDto.getPassword()));
            adminRepo.save(admin);
            return "updated";
        }catch(Exception e){
            return "Server Issue";
        }


//        if(tokenRepo.findFirstByTokenValue(tokenValue).getAdmin().getAdminEmail().equals(email)){
//
//            String password = changePasswordDto.getPassword();
//            try {
//                String passwordEncryptor = PasswordEncryptor.encrypt(password);
//
//                    AdminTokens adminTokens = new AdminTokens(token.getAdmin());
//                    ATokenService.createToken(adminTokens);
//                    token.setPassword(changePasswordDto.getPassword());
//                    ATokenService.deleteToken(tokenValue);
//                    return adminTokens.getTokenValue();
//
//
//            } catch (NoSuchAlgorithmException e) {
//
//                return "Authentication Invalid!!!";
//            }
//        }

    }
}
