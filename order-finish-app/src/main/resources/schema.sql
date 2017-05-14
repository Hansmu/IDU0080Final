DROP TABLE IF EXISTS made_order;

CREATE TABLE made_order
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tracking_number VARCHAR2(2000),
  courier_id BIGINT,
  order_id BIGINT,
  delivery_date VARCHAR2(20),
  order_price DOUBLE
);
