package com.example.Inventory.controller;

import com.example.Inventory.model.BookStock;
import com.example.Inventory.service.BookStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-stock")
public class BookStockController {

    @Autowired
    private BookStockService bookStockService;

    @GetMapping("/all")
    public List<BookStock> getAllStocks() {
        return bookStockService.getAllStocks();
    }

    @GetMapping("/stock/{bookId}")
    public ResponseEntity<BookStock> getStock(@PathVariable Long bookId) {
        return bookStockService.getStock(bookId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/stock/{bookId}")
    public ResponseEntity<BookStock> updateStock(@PathVariable Long bookId, @RequestBody int stock) {
        BookStock updatedBookStock = bookStockService.updateStock(bookId, stock);
        if (updatedBookStock != null) {
            return ResponseEntity.ok(updatedBookStock);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/stock/{bookId}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long bookId) {
        bookStockService.deleteStock(bookId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllStocks() {
        bookStockService.deleteAllStocks();
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<BookStock> addStock(@RequestBody BookStock newBookStock) {
        // Call the service method to add stock
        BookStock addedBookStock = bookStockService.addStock(newBookStock);
        // Return a response with the added stock and HTTP status 201 Created
        return new ResponseEntity<>(addedBookStock, HttpStatus.CREATED);
    }
}
