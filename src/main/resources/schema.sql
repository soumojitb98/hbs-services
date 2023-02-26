-- DDL Statement For creation of room_book_tbl

CREATE TABLE room_book_tbl (
booking_Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
cust_num INT NOT NULL,
cust_id INT,
check_In DATE NOT NULL,
check_out DATE NOT NULL,
no_Of_Rooms INT NOT NULL,
room_type_id INT,
FOREIGN KEY (room_type_id) REFERENCES room_type_tbl(id),
FOREIGN KEY (cust_id) REFERENCES customer_tbl(cust_id));

-- DDL Statement For Creation of room_type_tbl

CREATE TABLE room_type_tbl (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
type VARCHAR(45) NOT NULL,
price INT NOT NULL );

insert into room_type_tbl values (1,'STANDARD',2500), (2,'DELUXE',3500), (3,'COTTAGE',5500);

-- DDL Statement For Creation Of customer_tbl
CREATE TABLE customer_tbl (
cust_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
user_email VARCHAR(100) NOT NULL,
password CHAR(255) NOT NULL );