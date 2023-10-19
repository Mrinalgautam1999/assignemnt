package com.geekster.Restaurant.App.Controller;

import com.geekster.Restaurant.App.Model.DTO.*;
import com.geekster.Restaurant.App.Model.Orders;
import com.geekster.Restaurant.App.Model.Users.Admin;
import com.geekster.Restaurant.App.Model.Users.User;
import com.geekster.Restaurant.App.Service.OrderService;
import com.geekster.Restaurant.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;
    @PostMapping("user/signUp")
    public String userSignUp(@RequestBody User user){
        return userService.userSignUp(user);
    }

    @PostMapping("user/signIn")
    public String userSignIn(@RequestBody UserDto userDto){
        return userService.userSignIn(userDto);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestBody UserAuth userAuth)
    {
        return userService.userSignOut(userAuth);
    }

    @PostMapping("user/book/order")
    public String userBookOrder(@RequestBody UserInputDto input){
        return orderService.userBookOrder(input.getUserAuth(), input.getOrders());
    }
}
