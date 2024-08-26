package com.example.Inventory.mapper;

import com.example.Inventory.dto.StockDTO;
import com.example.Inventory.model.BookStock;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StockMapper {

    public StockDTO toDTO(BookStock bookStock) {
        if (bookStock == null) {
            return null;
        }
        return new StockDTO(bookStock.getBookId(), bookStock.getStock());
    }

    public BookStock toEntity(StockDTO stockDTO) {
        if (stockDTO == null) {
            return null;
        }
        return new BookStock(stockDTO.getBookId(), stockDTO.getStock());
    }
}
