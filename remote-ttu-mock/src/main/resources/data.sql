MERGE INTO courier (id, courier_name, percent_from_order) VALUES (1, 'DHL', 10);
MERGE INTO courier (id, courier_name, percent_from_order) VALUES (2, 'Omniva', 20);
MERGE INTO courier (id, courier_name, percent_from_order) VALUES (3, 'UPS', 15);
MERGE INTO courier (id, courier_name, percent_from_order) VALUES (4, 'Kenny B', 12);
MERGE INTO courier (id, courier_name, percent_from_order) VALUES (5, 'Mike D', 11);

MERGE INTO client_order (id, order_cost, delivery_address) VALUES (1, 1000, 'Tartumaa');
MERGE INTO client_order (id, order_cost, delivery_address) VALUES (2, 2000, 'Harjumaa');
MERGE INTO client_order (id, order_cost, delivery_address) VALUES (3, 3000, 'Virumaa');

MERGE INTO address (id, address) VALUES (1, 'Tartumaa');
MERGE INTO address (id, address) VALUES (2, 'Harjumaa');
MERGE INTO address (id, address) VALUES (3, 'Virumaa');
MERGE INTO address (id, address) VALUES (4, 'Saaremaa');
MERGE INTO address (id, address) VALUES (5, 'Raplamaa');

MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (1, 3, 1);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (2, 3, 2);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (3, 3, 3);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (4, 2, 4);
MERGE INTO order_seller_address (id, client_order_id, seller_address_id) VALUES (5, 1, 5);

MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(1, 1, 1);
MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(2, 2, 2);
MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(3, 3, 3);
MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(4, 4, 4);
MERGE INTO courier_address(id, courier_id, courier_address_id) VALUES(5, 5, 5);