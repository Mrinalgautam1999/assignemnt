package com.geekster.Restaurant.App.Controller;

import com.geekster.Restaurant.App.Model.DTO.AdminDto;
import com.geekster.Restaurant.App.Model.DTO.AdminAuth;
import com.geekster.Restaurant.App.Model.DTO.AdminInputDto;
import com.geekster.Restaurant.App.Model.DTO.ChangePasswordDto;
import com.geekster.Restaurant.App.Model.Orders;
import com.geekster.Restaurant.App.Model.Users.Admin;
import com.geekster.Restaurant.App.Service.AdminService;
import com.geekster.Restaurant.App.Service.FoodService;
import com.geekster.Restaurant.App.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    OrderService orderService;

    @Autowired
    FoodService foodService;

    @PostMapping("admin/signUp")
    public String adminSignUp(@RequestBody  Admin admin){
        return adminService.adminSignUp(admin);
    }

    @PostMapping("admin/signIn")
    public String adminSignIn(@RequestBody AdminDto adminDetail){
        return adminService.adminSignIn(adminDetail);
    }

    @DeleteMapping("admin/signOut")
    public String adminSignOut(@RequestBody AdminAuth adminAuth)
    {
        return adminService.adminSignOut(adminAuth);
    }

    @PostMapping("Food")
    public String addFoods(@RequestBody AdminInputDto adminAuth){
        return foodService.addFoods(adminAuth);
    }

    @GetMapping("all/order")
    public List<Orders> getAllOrders(@RequestBody AdminAuth adminAuth){
        return orderService.getAllOrders(adminAuth);
    }

    @GetMapping("forget/{adminEmail}/password")
    public String forgetPassword(@PathVariable String adminEmail){
        return adminService.forgetPassword(adminEmail);
    }

    @PutMapping("set/password")
    public String updatePassword(@RequestBody ChangePasswordDto changePasswordDto){
        return adminService.updatePassword(changePasswordDto);
    }

}
