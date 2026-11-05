CREATE TABLE IF NOT EXISTS articulos (
  idarticulo BIGINT PRIMARY KEY,
  nombrearticulo VARCHAR(100) NOT NULL,
  valor DOUBLE NOT NULL
);

INSERT INTO articulos (idarticulo, nombrearticulo, valor)
VALUES (1, 'Teclado', 50000), (2, 'Mouse', 25000);
