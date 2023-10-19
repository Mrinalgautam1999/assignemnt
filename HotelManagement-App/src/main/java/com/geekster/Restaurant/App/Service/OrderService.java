package com.geekster.Restaurant.App.Service;

import com.geekster.Restaurant.App.Model.DTO.AdminAuth;
import com.geekster.Restaurant.App.Model.DTO.UserAuth;
import com.geekster.Restaurant.App.Model.Food;
import com.geekster.Restaurant.App.Model.Orders;
import com.geekster.Restaurant.App.Model.Status;
import com.geekster.Restaurant.App.Model.Users.User;
import com.geekster.Restaurant.App.Repo.IFoodRepo;
import com.geekster.Restaurant.App.Repo.IOrderRepo;
import com.geekster.Restaurant.App.Repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    ATokenService aTokenService;

    @Autowired
    IFoodRepo foodRepo;

    @Autowired
    UTokenService uTokenService;
    public String userBookOrder(UserAuth userAuth, Orders orders) {
        if(uTokenService.authenticate(userAuth)){
            String email = userAuth.getUserEmail();
            User user = userRepo.findFirstByUserEmail(email);
            orders.setUser(user);

            Integer foodId = orders.getFood().getFoodId();
            Food food = foodRepo.findById(foodId).orElseThrow();
            orders.setFood(food);
            if(food != null) {
                orders.setQuantity(orders.getQuantity());
                orderRepo.save(orders);
                return "order Placed";
            }else{
                return "Invalid Order";
            }
        }else{
            return "UnAuthorised user";
        }
    }

    public List<Orders> getAllOrders(AdminAuth adminAuth) {
        if(aTokenService.authenticate(adminAuth)){
            return orderRepo.findAll();
        }
        return null;
    }
}
