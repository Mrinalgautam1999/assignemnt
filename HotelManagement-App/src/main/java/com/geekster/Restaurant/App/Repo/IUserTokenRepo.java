package com.geekster.Restaurant.App.Repo;

import com.geekster.Restaurant.App.Model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserTokenRepo extends JpaRepository<UserToken, Integer> {

    UserToken findFirstByTokenValue(String tokenValue);
}
