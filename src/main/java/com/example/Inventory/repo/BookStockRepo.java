package com.example.Inventory.repo;

import com.example.Inventory.model.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStockRepo extends JpaRepository<BookStock, Long> {
}
