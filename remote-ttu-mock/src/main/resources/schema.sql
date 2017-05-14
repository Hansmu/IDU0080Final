DROP TABLE IF EXISTS client_order;

CREATE TABLE courier
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  courier_name VARCHAR2(2000),
  address_id BIGINT
);

CREATE TABLE client_order
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_cost FLOAT,
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
