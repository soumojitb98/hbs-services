-- DDL Statement For creation of room_book_tbl

CREATE TABLE room_book_tbl (
    booking_id SERIAL PRIMARY KEY,
    cust_num INT NOT NULL,
    cust_id INT,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    no_of_rooms INT NOT NULL,
    room_type_id INT,
    FOREIGN KEY (room_type_id) REFERENCES room_type_tbl(id),
    FOREIGN KEY (cust_id) REFERENCES customer_tbl(cust_id)
);

-- DDL Statement For Creation of room_type_tbl

CREATE TABLE room_type_tbl (
id SERIAL PRIMARY KEY,
type VARCHAR(45) NOT NULL,
price INT NOT NULL );

insert into room_type_tbl values (1,'STANDARD',2500), (2,'DELUXE',3500), (3,'COTTAGE',5500);

-- DDL Statement For Creation Of customer_tbl
CREATE TABLE customer_tbl (
    cust_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(45) NOT NULL
);