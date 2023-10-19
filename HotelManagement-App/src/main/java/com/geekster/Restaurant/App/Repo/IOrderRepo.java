package com.geekster.Restaurant.App.Repo;

import com.geekster.Restaurant.App.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Orders, Integer> {
}
