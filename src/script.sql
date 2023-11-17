create database randomn;
use randomn;
CREATE TABLE Category (
                          id INT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          parent_category_id INT,
                          FOREIGN KEY (parent_category_id) REFERENCES Category(id)
);

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

CALL GetAllProductsForCategory(1);