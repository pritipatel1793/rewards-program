-- Insert sample customers
INSERT INTO customer (name, email) VALUES ('Priti', 'priti@gmail.com');
INSERT INTO customer (name, email) VALUES ('Riya', 'riya@gmail.com');
INSERT INTO customer (name, email) VALUES ('Ashish', 'ashish@gmail.com');

-- Insert sample transactions for Priti (customer_id = 1)
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (1, 120.00, '2025-03-15 10:00:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (1, 75.00, '2025-02-20 15:30:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (1, 200.00, '2025-01-10 09:15:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (1, 150.00, '2024-12-05 14:20:00');

-- Insert sample transactions for Riya (customer_id = 2)
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (2, 150.00, '2025-03-01 14:20:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (2, 85.00, '2025-02-15 11:45:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (2, 95.00, '2025-01-05 16:30:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (2, 180.00, '2024-12-20 09:00:00');

-- Insert sample transactions for Ashish (customer_id = 3)
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (3, 250.00, '2025-03-10 09:30:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (3, 120.00, '2025-02-25 16:45:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (3, 300.00, '2025-01-15 11:20:00');
INSERT INTO transaction (customer_id, amount, timestamp) VALUES (3, 175.00, '2024-12-30 13:15:00'); 