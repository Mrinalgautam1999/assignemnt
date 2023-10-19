package com.geekster.Restaurant.App.Repo;

import com.geekster.Restaurant.App.Model.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {

    User findFirstByUserEmail(String email);
}
