DROP TABLE IF EXISTS courier;
DROP TABLE IF EXISTS client_order;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS order_seller_address;
DROP TABLE IF EXISTS courier_address;

CREATE TABLE courier
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  courier_name VARCHAR2(2000),
  percent_from_order INT
);

CREATE TABLE client_order
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_cost NUMERIC,
  delivery_address VARCHAR2(500)
);

CREATE TABLE address
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  address VARCHAR2(2000)
);

CREATE TABLE order_seller_address
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  client_order_id BIGINT,
  seller_address_id BIGINT
);

CREATE TABLE courier_address
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  courier_id BIGINT,
  courier_address_id BIGINT
);