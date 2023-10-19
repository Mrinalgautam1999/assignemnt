package com.geekster.Restaurant.App.Model.DTO;


import com.geekster.Restaurant.App.Model.Food;
import com.geekster.Restaurant.App.Model.Orders;
import com.geekster.Restaurant.App.Model.Users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto {

    UserAuth userAuth;
    Orders orders;
}
