package com.learning.java.springlamiapizzeriacrud.repo;

import com.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
    void deleteBy(Integer id);
}
