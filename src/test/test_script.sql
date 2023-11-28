create database test_randomn;
use test_randomn;
create TABLE Category (
                          id INT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          parent_category_id INT,
                          FOREIGN KEY (parent_category_id) REFERENCES Category(id)
);

DELETE FROM Category WHERE parent_category_id IS NOT NULL AND parent_category_id NOT IN (SELECT id FROM Category);
update Category set parent_category_id=null where parent_category_id is not null;

ALTER TABLE Category
    ADD CONSTRAINT fk_parent_category
        FOREIGN KEY (parent_category_id)
            REFERENCES Category(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

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

ALTER TABLE Category
    ADD CONSTRAINT unique_category_name UNIQUE (name);


ALTER TABLE Product DROP FOREIGN KEY Product_ibfk_1;
ALTER TABLE Category DROP FOREIGN KEY Category_ibfk_1;


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

ALTER TABLE order_t_Item
    DROP FOREIGN KEY order_t_Item_ibfk_1;
    
ALTER TABLE order_t_Item
    DROP PRIMARY KEY;
    
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

select *from Admin;
select *from Product;
select *from Category;

ALTER TABLE Admin
DROP COLUMN name;


select *from Category;
select *from Product;
select *from Operator;
select *from order_t_Item;
select *from Admin;
SHOW CREATE TABLE order_t_Item;
select *from order_t_Item;
select *from order_t;
