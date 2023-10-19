package com.geekster.Restaurant.App.Repo;

import com.geekster.Restaurant.App.Model.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin, Integer> {


    Admin findByAdminEmail(String email);

    Admin findByAdminId(Admin existingAdmin);

}
