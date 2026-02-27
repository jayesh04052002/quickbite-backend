-- Clear old data (order matters because of FK)
DELETE FROM payment;
DELETE FROM orders;
DELETE FROM menu_item;
DELETE FROM restaurant;
-- users table only if you want reset users also:
-- DELETE FROM users;

-- 4 Restaurants
INSERT INTO restaurant (id, name, address) VALUES
(1, 'Burger King', 'Andheri West, Mumbai'),
(2, 'Pizza Hut', 'Dadar, Mumbai'),
(3, 'Subway', 'Powai, Mumbai'),
(4, 'Cafe Coffee Day', 'Bandra, Mumbai');

-- Burger King Menu (restaurant_id = 1)
INSERT INTO menu_item (name, price, restaurant_id) VALUES
('Whopper', 199, 1),
('Chicken Whopper', 229, 1),
('Veg Burger', 99, 1),
('French Fries', 79, 1),
('Coke (300ml)', 49, 1);

-- Pizza Hut Menu (restaurant_id = 2)
INSERT INTO menu_item (name, price, restaurant_id) VALUES
('Margherita Pizza', 199, 2),
('Farmhouse Pizza', 299, 2),
('Paneer Pizza', 329, 2),
('Garlic Bread', 129, 2),
('Pepsi (300ml)', 49, 2);

-- Subway Menu (restaurant_id = 3)
INSERT INTO menu_item (name, price, restaurant_id) VALUES
('Veg Sub 6 inch', 159, 3),
('Chicken Sub 6 inch', 189, 3),
('Paneer Tikka Sub', 179, 3),
('Cookie', 49, 3),
('Lemon Iced Tea', 79, 3);

-- CCD Menu (restaurant_id = 4)
INSERT INTO menu_item (name, price, restaurant_id) VALUES
('Cappuccino', 149, 4),
('Cold Coffee', 179, 4),
('Chocolate Shake', 199, 4),
('Veg Sandwich', 129, 4),
('French Fries', 89, 4);