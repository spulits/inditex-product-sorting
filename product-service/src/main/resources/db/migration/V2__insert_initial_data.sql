INSERT INTO products (id, name, sales_units, stock_s, stock_m, stock_l) 
VALUES 
(1, 'V-NECH BASIC SHIRT', 100, 4, 9, 0),
(2, 'CONTRASTING FABRIC T-SHIRT', 50, 35, 9, 9),
(3, 'RAISED PRINT T-SHIRT', 80, 20, 2, 20),
(4, 'PLEATED T-SHIRT', 3, 25, 30, 10),
(5, 'CONTRASTING LACE T-SHIRT', 650, 0, 1, 0),
(6, 'SLOGAN T-SHIRT', 20, 9, 2, 5);

-- Reiniciamos la secuencia para que el próximo ID autogenerado sea 7
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));