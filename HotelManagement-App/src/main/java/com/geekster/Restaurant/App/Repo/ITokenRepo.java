package com.geekster.Restaurant.App.Repo;

import com.geekster.Restaurant.App.Model.AdminTokens;
import com.geekster.Restaurant.App.Model.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITokenRepo extends JpaRepository<AdminTokens, Integer> {
    AdminTokens findFirstByTokenValue(String tokenValue);

    AdminTokens findByTokenValue(String tokenValue);


    AdminTokens findByAdmin(Admin existingAdmin);
}
