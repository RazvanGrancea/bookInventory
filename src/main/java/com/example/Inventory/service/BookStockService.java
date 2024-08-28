package com.example.Inventory.service;

import com.example.Inventory.dto.StockDTO;
import com.example.Inventory.mapper.StockMapper;
import com.example.Inventory.model.BookStock;
import com.example.Inventory.repo.BookStockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookStockService {

    @Autowired
    private BookStockRepo bookStockRepo;

    public List<StockDTO> getAllStocks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookStock> bookStockPage = bookStockRepo.findAll(pageable);

        return bookStockPage.stream()
                .map(StockMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<StockDTO> getStock(Long bookId) {
        return bookStockRepo.findById(bookId)
                .map(StockMapper::toDTO);
    }

    public Optional<StockDTO> updateStock(Long bookId, int stock) {
        Optional<BookStock> optionalBookStock = bookStockRepo.findById(bookId);
        if (optionalBookStock.isPresent()) {
            BookStock bookStock = optionalBookStock.get();
            bookStock.setStock(stock);
            BookStock updatedBookStock = bookStockRepo.save(bookStock);
            return Optional.of(StockMapper.toDTO(updatedBookStock));
        }
        return Optional.empty();
    }

    public void deleteStock(Long bookId) {
        bookStockRepo.deleteById(bookId);
    }

    public void deleteAllStocks() {
        bookStockRepo.deleteAll();
    }

    public StockDTO addStock(Long bookId, int updatedStock) {
        Optional<BookStock> optionalBookStock = bookStockRepo.findById(bookId);

        BookStock bookStock;
        if (optionalBookStock.isPresent()) {
            bookStock = optionalBookStock.get();
            bookStock.setStock(updatedStock);
        } else {
            bookStock = new BookStock(bookId, updatedStock);
        }

        BookStock savedBookStock = bookStockRepo.save(bookStock);
        return StockMapper.toDTO(savedBookStock);
    }
}
