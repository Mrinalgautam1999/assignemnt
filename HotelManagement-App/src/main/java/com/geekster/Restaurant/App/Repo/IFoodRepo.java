package com.geekster.Restaurant.App.Repo;

import com.geekster.Restaurant.App.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodRepo extends JpaRepository<Food, Integer> {
}
