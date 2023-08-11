CREATE TABLE Customer (
    customer_id varchar(40) NOT NULL,
    customer_name varchar(255),
    address varchar(255),
    phone int4,
    PRIMARY KEY(customer_id)
);

CREATE TABLE Product (
    product_id varchar(40) NOT NULL,
    product_price int4,
    product_description varchar(255),
    stock int4,
    PRIMARY KEY(product_id)
);

CREATE TABLE IF NOT EXISTS "order" (
     order_id varchar(40) NOT NULL,
     customer_id varchar(40) NOT NULL,
     customer_name varchar(255),
     amount numeric(19, 2),
     quantity int4,
     product_id varchar(40) NOT NULL,
     order_date timestamptz default now(),
    primary key(order_id),
    CONSTRAINT order_fk1 FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
    CONSTRAINT order_fk2 FOREIGN KEY(product_id) REFERENCES product(product_id)
);
