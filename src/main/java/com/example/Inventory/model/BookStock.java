package com.example.Inventory.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStock {
    @Id
    @Column(name = "bookid")
    private Long bookId;

    @Column(name = "stock")
    private Integer stock;
}
