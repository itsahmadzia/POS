
CREATE database employee_management;
use employee_management;
CREATE TABLE Employee (
                         id INT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         salary DECIMAL(10, 2) NOT NULL,
                        role VARCHAR(255) NOT NULL
);

select *from Employee;