package com.geekster.Restaurant.App.Model.DTO;

import com.geekster.Restaurant.App.Model.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminInputDto {

     AdminAuth auth;
     Food food;
}
