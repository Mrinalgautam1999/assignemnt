package com.geekster.Restaurant.App.Service;

import com.geekster.Restaurant.App.Model.DTO.AdminAuth;
import com.geekster.Restaurant.App.Model.AdminTokens;
import com.geekster.Restaurant.App.Repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATokenService {

    @Autowired
    ITokenRepo tokenRepo;

    public void createToken(AdminTokens adminTokens) {

        tokenRepo.save(adminTokens);
    }


    public boolean authenticate(AdminAuth adminAuth) {
        String email = adminAuth.getAdminEmail();
        String tokenValue = adminAuth.getTokenValue();

        //find thr actual token -> get the connected patient -> get its email-> verify with passed email

        //return ipTokenRepo.findFirstByTokenValue(tokenValue).getPatient().getPatientEmail().equals(email);

        AdminTokens token =  tokenRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getAdmin().getAdminEmail().equals(email);
        }
        else
        {
            return false;
        }
    }

    public void deleteToken(String tokenValue) {

        AdminTokens token = tokenRepo.findFirstByTokenValue(tokenValue);
        tokenRepo.delete(token);
    }
}
