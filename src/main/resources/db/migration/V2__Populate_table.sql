DO $$
BEGIN
    FOR i IN 1..100000 LOOP
        INSERT INTO book_stock (bookid, stock)
        VALUES (i, FLOOR(RANDOM() * 100 +1 ));
     END LOOP;
END $$