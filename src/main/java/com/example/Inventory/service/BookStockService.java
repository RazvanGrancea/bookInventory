package com.example.Inventory.service;

import com.example.Inventory.model.BookStock;
import com.example.Inventory.repo.BookStockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStockService {

    @Autowired
    private BookStockRepo bookStockRepo;

    public List<BookStock> getAllStocks(){
        return bookStockRepo.findAll();
    }
    public Optional<BookStock> getStock(Long bookId){
        return bookStockRepo.findById(bookId);
    }

    public BookStock updateStock(Long bookId, int stock) {
        Optional<BookStock> optionalBookStock = bookStockRepo.findById(bookId);
        if (optionalBookStock.isPresent()) {
            BookStock bookStock = optionalBookStock.get();
            bookStock.setStock(stock);
            return bookStockRepo.save(bookStock);
        }
        return null;
    }

    public void deleteStock(Long bookId){
        bookStockRepo.deleteById(bookId);
    }
    public void deleteAllStocks(){
        bookStockRepo.deleteAll();
    }
    public BookStock addStock(BookStock newBookStock) {
        return bookStockRepo.save(newBookStock);
    }
}
