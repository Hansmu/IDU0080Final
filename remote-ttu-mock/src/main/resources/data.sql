MERGE INTO courier (id, courier_name) VALUES (1, 'DHL');
MERGE INTO courier (id, courier_name) VALUES (2, 'Omniva');
MERGE INTO courier (id, courier_name) VALUES (3, 'UPS');

MERGE INTO client_order (id, order_cost, delivery_address) VALUES (1, 1000, 'China');
MERGE INTO client_order (id, order_cost, delivery_address) VALUES (2, 2000, 'Japan');
MERGE INTO client_order (id, order_cost, delivery_address) VALUES (3, 3000, 'Russia');

MERGE INTO address (id, address) VALUES (1, 'Germany');
MERGE INTO address (id, address) VALUES (2, 'Estonia');
MERGE INTO address (id, address) VALUES (3, 'USA');

MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (1, 3, 1);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (1, 3, 2);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (1, 3, 3);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (2, 2, 2);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (3, 1, 3);

MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(1, 1, 1);
MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(1, 2, 2);
MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(1, 3, 3);