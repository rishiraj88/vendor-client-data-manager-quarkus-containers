CREATE TABLE products (
    product_id BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE,
    price NUMERIC(8,2)
);

CREATE TABLE customers (
    customer_id BIGSERIAL PRIMARY KEY,
    fname VARCHAR,
    lname VARCHAR,
    email VARCHAR,
    phone VARCHAR,
    address VARCHAR
);

CREATE TABLE vendors (
    vendor_id BIGSERIAL PRIMARY KEY,
    name VARCHAR,
    spoc VARCHAR,
    phone VARCHAR,
    email VARCHAR,
    address VARCHAR
);

CREATE INDEX vendor_name_id ON vendors(name);