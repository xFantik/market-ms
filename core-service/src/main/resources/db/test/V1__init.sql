CREATE TABLE products
(
    id     bigserial,  //то же самое, что bigint autoincrement
    title  VARCHAR(255),
    price  int,
    active boolean,
    PRIMARY KEY (id)
);
INSERT INTO products (title, price, active)
VALUES ('Bread', 10, true),
       ('Jam', 15, true),
       ('Cherry', 120, true),
       ('Milk', 120, false);

