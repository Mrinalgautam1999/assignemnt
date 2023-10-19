package com.geekster.Restaurant.App.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {
    private String userEmail;
    private String tokenValue;
}
