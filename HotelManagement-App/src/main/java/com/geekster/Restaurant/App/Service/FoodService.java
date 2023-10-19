package com.geekster.Restaurant.App.Service;

import com.geekster.Restaurant.App.Model.DTO.AdminAuth;
import com.geekster.Restaurant.App.Model.DTO.AdminInputDto;
import com.geekster.Restaurant.App.Repo.IFoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    IFoodRepo foodRepo;

    @Autowired
    ATokenService ATokenService;
    public String addFoods(AdminInputDto adminAuth) {
        AdminAuth admin = adminAuth.getAuth();
        if(ATokenService.authenticate(admin)){
            foodRepo.save(adminAuth.getFood());
            return "food added";
        }else{
            return "Invalid access";
        }
    }
}
