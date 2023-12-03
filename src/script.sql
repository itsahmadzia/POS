create database randomn;
use randomn;
CREATE TABLE Category (
                          id INT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          parent_category_id INT,
                          FOREIGN KEY (parent_category_id) REFERENCES Category(id)
);
select *from Category;


DELETE FROM Category WHERE parent_category_id IS NOT NULL AND parent_category_id NOT IN (SELECT id FROM Category);
update Category set parent_category_id=null where parent_category_id is not null;

ALTER TABLE Category
    ADD CONSTRAINT fk_parent_category
        FOREIGN KEY (parent_category_id)
            REFERENCES Category(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

SELECT
    oi.product_name,
    SUM(oi.item_price) AS total_sum
FROM
    order_t o
        JOIN order_t_Item oi ON o.id = oi.order_id
WHERE
        o.order_date = CURDATE()
GROUP BY
    oi.product_name;



CREATE TABLE Product (
                         id INT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         stock_quantity INT NOT NULL,
                         quantity_per_pack INT NOT NULL,
                         description TEXT,
                         category_id INT,
                         FOREIGN KEY (category_id) REFERENCES Category(id)
);
ALTER TABLE Product
    ADD CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES Category(id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;
select *from Category;
ALTER TABLE Category
    ADD CONSTRAINT unique_category_name UNIQUE (name);


ALTER TABLE Product DROP FOREIGN KEY Product_ibfk_1;
ALTER TABLE Category DROP FOREIGN KEY Category_ibfk_1;
SHOW CREATE TABLE Product;
SHOW CREATE TABLE Category;
select *from Admin;
ALTER TABLE Category add column Description varchar(500);

    ALTER TABLE Product
    ADD COLUMN expiryDate DATE;
CREATE TABLE order_t (
                         id INT PRIMARY KEY,
                         customer_name VARCHAR(255) NOT NULL,
                         total DECIMAL(10, 2) NOT NULL,
                         order_date DATE NOT NULL
);

CREATE TABLE order_t_Item (
                              order_id INT,
                              product_id INT,
                              PRIMARY KEY (order_id, product_id),
                              quantity_ordered INT NOT NULL,
                              price DECIMAL(10, 2) NOT NULL,
                              FOREIGN KEY (order_id) REFERENCES order_t(id),
                              FOREIGN KEY (product_id) REFERENCES Product(id)
);
DESC order_t_Item;
SELECT * FROM order_t_Item;
select *
from order_t;

SHOW CREATE TABLE order_t_Item;
ALTER TABLE order_t_Item
    DROP PRIMARY KEY;

ALTER TABLE order_t_Item
    DROP FOREIGN KEY order_t_Item_ibfk_1;

ALTER TABLE order_t
    MODIFY id INT AUTO_INCREMENT;

ALTER TABLE order_t_Item
    ADD CONSTRAINT order_t_Item_ibfk_1
        FOREIGN KEY (order_id) REFERENCES order_t(id);

CREATE INDEX idx_product_price ON Product (price);

ALTER TABLE order_t_Item DROP FOREIGN KEY order_t_Item_ibfk_2;
ALTER TABLE order_t_Item DROP COLUMN product_id;
ALTER TABLE order_t
    MODIFY id INT AUTO_INCREMENT;



CREATE INDEX idx_price ON Product (price);

ALTER TABLE order_t_Item
ADD COLUMN product_price DECIMAL(10, 2),
ADD CONSTRAINT fk_order_item_product
    FOREIGN KEY (price)
    REFERENCES Product(price)
    ON UPDATE CASCADE
    ON DELETE NO ACTION;

CREATE INDEX idx_product_name ON Product (name);

ALTER TABLE order_t_Item
ADD COLUMN product_name VARCHAR(255),
ADD CONSTRAINT fk_order_item_product_name
    FOREIGN KEY (product_name)
    REFERENCES Product(name)
    ON UPDATE CASCADE
    ON DELETE NO ACTION;

ALTER TABLE order_t_Item
CHANGE COLUMN product_price item_price DECIMAL(10,2) DEFAULT NULL;
-- price is product price, price_item is ordered item price

SELECT * FROM Category;
SELECT * FROM Product;
SELECT * FROM order_t;

SELECT * FROM order_t_Item;

INSERT INTO Category (id, name, parent_category_id) VALUES
                                                        (1, 'Pharmacy', NULL),
                                                        (2, 'Medications', 1),
                                                        (3, 'Over-the-Counter (OTC)', 2);


INSERT INTO Category (id, name, parent_category_id) VALUES
    (4, 'Prescription Medications', 2);

INSERT INTO Product (id, name, price, stock_quantity, quantity_per_pack, description, category_id) VALUES
                                                                                                       (1, 'Aspirin', 5.99, 100, 10, 'Pain reliever', 2),
                                                                                                       (2, 'Ibuprofen', 7.99, 50, 20, 'Pain and fever reducer', 2),
                                                                                                       (3, 'Lipitor', 12.99, 30, 30, 'Cholesterol medication', 4),
                                                                                                       (4, 'Vitamin C', 3.99, 200, 1, 'Dietary supplement', 3);
CREATE TABLE Admin (
                       username VARCHAR(255) PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE Manager (
                         username VARCHAR(255) PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE Operator (
                          username VARCHAR(255) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL
);

ALTER TABLE order_t
    ADD COLUMN operator_username VARCHAR(255),
    ADD FOREIGN KEY (operator_username) REFERENCES Operator(username);



DELIMITER //
CREATE PROCEDURE GetALLProductsForCategory(IN category_id INT)
BEGIN
    WITH RECURSIVE CategoryTree AS (
        SELECT
            c.id,
            c.name AS category_name,
            c.parent_category_id,
            0 AS level
        FROM Category c
        WHERE c.id = category_id
        UNION ALL
        SELECT
            c.id,
            c.name AS category_name,
            c.parent_category_id,
            ct.level + 1
        FROM CategoryTree ct
                 JOIN Category c ON ct.id = c.parent_category_id
    )
    SELECT
        p.name AS product_name
    FROM CategoryTree
             LEFT JOIN Product p ON CategoryTree.id = p.category_id;
END //
DELIMITER ;


CALL GetAllProductsForCategory(4);

drop PROCEDURE GetProductsForCategoryConcat;

DELIMITER //
CREATE PROCEDURE GetProductsForCategoryConcat(IN category_id INT)
BEGIN
    WITH RECURSIVE CategoryTree AS (
        SELECT
            c.id,
            c.name AS category_name,
            c.parent_category_id,
            0 AS level
        FROM Category c
        WHERE c.id = category_id
        UNION ALL
        SELECT
            c.id,
            c.name AS category_name,
            c.parent_category_id,
            ct.level + 1
        FROM CategoryTree ct
                 JOIN Category c ON ct.id = c.parent_category_id
    )
    SELECT
        CONCAT_WS('|', p.id, p.name) AS product_info
    FROM CategoryTree
             LEFT JOIN Product p ON CategoryTree.id = p.category_id;
END //
DELIMITER ;

CALL  GetProductsForCategoryConcat(2);
select *from Admin;
select *from Product;
select *from Category;

ALTER TABLE Admin
DROP COLUMN name;

INSERT INTO Admin (username, password)
VALUES ('admin1', 'adminpassword1');

INSERT INTO Manager (username, name, password)
VALUES ('manager1', 'Manager One', 'managerpassword1');

INSERT INTO Operator (username, name, password)
VALUES ('operator1', 'Operator One', 'operatorpassword1');

select *from Operator;


select *from Product;
select *from Operator;
select *from Manager;
select *from Admin;

select *from order_t_Item;
select *
from order_t;

update Category set parent_category_id=null;

update Product set stock_quantity=20 where name like '%f%';

delete from order_t_Item where randomn.order_t_Item.item_price=0


SELECT
    oi.product_name,
    SUM(oi.item_price) AS total_sum
FROM
    order_t o
        JOIN order_t_Item oi ON o.id = oi.order_id
WHERE
    o.order_date BETWEEN '2022-01-02' AND '2023-11-30'
GROUP BY
    oi.product_name;



SELECT
    oi.product_name,
    SUM(oi.item_price) AS total_sum
FROM
    order_t o
        JOIN order_t_Item oi ON o.id = oi.order_id
WHERE
        o.order_date ='2023-11-29'
GROUP BY
    oi.product_name;


select *from Product;
update Product set Product.expiryDate='2023-12-23' where id = 5;


SELECT c.name AS category_name, COUNT(p.id) AS number_of_products
FROM Category c
         LEFT JOIN Product p ON c.id = p.category_id
GROUP BY c.id, c.name;

select *from order_t;


SELECT
    order_t.operator_username AS name,
    COUNT(id) AS number_of_orders,
    SUM(total) AS total_amount
FROM
    order_t
GROUP BY
    order_t.operator_username;


select *from order_t_Item;

SHOW CREATE TABLE order_t_Item;
drop table order_t_Item;

CREATE TABLE `order_t_Item` (
                                `order_id` int NOT NULL,
                                `quantity_ordered` int NOT NULL,
                                `price` decimal(10,2) NOT NULL,
                                `product_name` varchar(255) DEFAULT NULL,
                                `item_price` decimal(10,2) DEFAULT NULL,
                                KEY `fk_order_item_product_name` (`product_name`),
                                KEY `order_t_Item_ibfk_1` (`order_id`),
                                CONSTRAINT `fk_order_item_product_name` FOREIGN KEY (`product_name`) REFERENCES `Product` (`name`) on UPDATE no action  on delete no action,
                                CONSTRAINT `order_t_Item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci