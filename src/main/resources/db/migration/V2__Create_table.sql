DROP TABLE IF EXISTS book_stock;
CREATE TABLE book_stock (
    bookid BIGINT PRIMARY KEY,
    stock INTEGER NOT NULL
);