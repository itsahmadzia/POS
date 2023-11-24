create database randomn2;
use randomn2;
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

SELECT * FROM order_t_Item;
SHOW CREATE TABLE order_t_Item;

CREATE INDEX idx_product_price ON Product (price);

ALTER TABLE order_t_Item DROP FOREIGN KEY order_t_Item_ibfk_2;
ALTER TABLE order_t_Item DROP COLUMN product_id;

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

select *from Admin;
select *from Product;
select *from Category;
INSERT INTO Admin (username, name, password)
VALUES ('admin1', 'Admin One', 'adminpassword1');

INSERT INTO Manager (username, name, password)
VALUES ('manager1', 'Manager One', 'managerpassword1');

INSERT INTO Operator (username, name, password)
VALUES ('operator1', 'Operator One', 'operatorpassword1');

ALTER TABLE Admin
DROP COLUMN name;


select *from Product;
select *from Operator;
select *from Manager;
select *from Admin;
