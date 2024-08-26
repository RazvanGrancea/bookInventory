package com.example.Inventory.controller;

import com.example.Inventory.dto.StockDTO;
import com.example.Inventory.service.BookStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class BookStockController {

    @Autowired
    private BookStockService bookStockService;

    @GetMapping
    public List<StockDTO> getAllStocks() {
        return bookStockService.getAllStocks();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Long bookId) {
        Optional<StockDTO> stockDTO = bookStockService.getStock(bookId);
        return stockDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Long bookId, @RequestParam int stock) {
        Optional<StockDTO> updatedStock = bookStockService.updateStock(bookId, stock);
        return updatedStock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long bookId) {
        bookStockService.deleteStock(bookId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllStocks() {
        bookStockService.deleteAllStocks();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<StockDTO> addStock(@PathVariable Long bookId, @RequestParam int stock) {
        StockDTO stockDTO = bookStockService.addStock(bookId, stock);
        return ResponseEntity.ok(stockDTO);
    }
}
