INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Brioche', 35000, 'https://yourwebsite.com/images/brioche.jpg', 'A soft, slightly sweet, and buttery bread', (SELECT id FROM `categories` WHERE name = 'Buns'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE('Hot Cross Buns', 40000, 'https://yourwebsite.com/images/hot_cross_buns.jpg', 'Traditional sweet buns with spices and raisins, marked with a cross on top', (SELECT id FROM `categories` WHERE name = 'Buns'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE('Bao Buns', 50000, 'https://yourwebsite.com/images/bao_buns.jpg', 'Steamed buns filled with various fillings, popular in East Asian cuisine', (SELECT id FROM `categories` WHERE name = 'Buns'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE('Cinnamon Rolls', 45000, 'https://yourwebsite.com/images/cinnamon_rolls.jpg', 'Sweet rolls with cinnamon filling, topped with icing', (SELECT id FROM `categories` WHERE name = 'Buns'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE('Garlic Buns', 30000, 'https://yourwebsite.com/images/garlic_buns.jpg', 'Soft buns with a savory garlic butter filling', (SELECT id FROM `categories` WHERE name = 'Buns'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Avocado Toast', 55000, 'https://yourwebsite.com/images/avocado_toast.jpg', 'Toasted bread topped with mashed avocado, salt, pepper, and optional seasonings', (SELECT id FROM `categories` WHERE name = 'Toasts'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('French Toast', 60000, 'https://yourwebsite.com/images/french_toast.jpg', 'Bread soaked in egg mixture, fried until golden brown, and often topped with syrup', (SELECT id FROM `categories` WHERE name = 'Toasts'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Garlic Toast', 30000, 'https://yourwebsite.com/images/garlic_toast.jpg', 'Toasted bread topped with garlic butter', (SELECT id FROM `categories` WHERE name = 'Toasts'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Club Sandwich', 70000, 'https://yourwebsite.com/images/club_sandwich.jpg', 'A classic sandwich with layers of turkey, bacon, lettuce, tomato, and mayonnaise', (SELECT id FROM `categories` WHERE name = 'Sandwiches'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('BLT Sandwich', 65000, 'https://yourwebsite.com/images/blt_sandwich.jpg', 'Bacon, lettuce, and tomato sandwich', (SELECT id FROM `categories` WHERE name = 'Sandwiches'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Egg Salad Sandwich', 60000, 'https://yourwebsite.com/images/egg_salad_sandwich.jpg', 'A sandwich filled with egg salad', (SELECT id FROM `categories` WHERE name = 'Sandwiches'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Pound Cake', 50000, 'https://yourwebsite.com/images/pound_cake.jpg', 'A dense cake made with equal weights of flour, butter, eggs, and sugar', (SELECT id FROM `categories` WHERE name = 'Dry Cakes'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Marble Cake', 55000, 'https://yourwebsite.com/images/marble_cake.jpg', 'A cake with swirls of chocolate and vanilla', (SELECT id FROM `categories` WHERE name = 'Dry Cakes'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Red Velvet Cake', 60000, 'https://yourwebsite.com/images/red_velvet_cake.jpg', 'A soft, moist cake with a slight cocoa flavor and a deep red color', (SELECT id FROM `categories` WHERE name = 'Cakes'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Black Forest Cake', 75000, 'https://yourwebsite.com/images/black_forest_cake.jpg', 'A German dessert with layers of chocolate sponge cake, whipped cream, and cherries', (SELECT id FROM `categories` WHERE name = 'Cakes'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Lemon Cake Slice', 20000, 'https://yourwebsite.com/images/lemon_cake_slice.jpg', 'A slice of lemon-flavored cake', (SELECT id FROM `categories` WHERE name = 'Cake Slices'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Chocolate Cake Slice', 25000, 'https://yourwebsite.com/images/chocolate_cake_slice.jpg', 'A slice of rich chocolate cake', (SELECT id FROM `categories` WHERE name = 'Cake Slices'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Pumpkin Pie (Thanksgiving)', 50000, 'https://yourwebsite.com/images/pumpkin_pie.jpg', 'Seasonal pie made with pumpkin, perfect for fall', (SELECT id FROM `categories` WHERE name = 'Seasonal Specials'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Yule Log (Christmas)', 80000, 'https://yourwebsite.com/images/yule_log.jpg', 'A traditional Christmas dessert shaped like a log', (SELECT id FROM `categories` WHERE name = 'Seasonal Specials'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Bread Pudding', 45000, 'https://yourwebsite.com/images/bread_pudding.jpg', 'A warm and cozy dessert made from bread soaked in custard and baked', (SELECT id FROM `categories` WHERE name = 'Pudding'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Rice Pudding', 35000, 'https://yourwebsite.com/images/rice_pudding.jpg', 'A creamy dessert made from rice, milk, and sugar', (SELECT id FROM `categories` WHERE name = 'Pudding'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Hot Chocolate', 30000, 'https://yourwebsite.com/images/hot_chocolate.jpg', 'A warm chocolate drink', (SELECT id FROM `categories` WHERE name = 'Drinks'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Iced Latte', 40000, 'https://yourwebsite.com/images/iced_latte.jpg', 'A chilled espresso-based drink with milk', (SELECT id FROM `categories` WHERE name = 'Drinks'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Chocolate Chip Cookies', 15000, 'https://yourwebsite.com/images/chocolate_chip_cookies.jpg', 'Crispy and chewy cookies loaded with chocolate chips', (SELECT id FROM `categories` WHERE name = 'Cookies'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Oatmeal Raisin Cookies', 18000, 'https://yourwebsite.com/images/oatmeal_raisin_cookies.jpg', 'Cookies with oatmeal and raisins', (SELECT id FROM `categories` WHERE name = 'Cookies'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Plain Bagel', 25000, 'https://yourwebsite.com/images/plain_bagel.jpg', 'A dense and chewy bread with a ring shape', (SELECT id FROM `categories` WHERE name = 'Bagels'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Sesame Bagel', 30000, 'https://yourwebsite.com/images/sesame_bagel.jpg', 'A bagel topped with sesame seeds', (SELECT id FROM `categories` WHERE name = 'Bagels'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Apple Pie', 55000, 'https://yourwebsite.com/images/apple_pie.jpg', 'A classic pie with a filling of apples and spices, enclosed in a flaky crust', (SELECT id FROM `categories` WHERE name = 'Pies'));
INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Pumpkin Pie', 50000, 'https://yourwebsite.com/images/pumpkin_pie.jpg', 'Seasonal pie made with pumpkin, perfect for fall', (SELECT id FROM `categories` WHERE name = 'Pies'));

INSERT INTO products (name, price, thumbnail, description, category_id) VALUE ('Opera Cake', 80000, 'https://yourwebsite.com/images/opera_cake.jpg', 'A French dessert with layers of almond sponge cake soaked in coffee syrup, layered with chocolate and coffee buttercream', (SELECT id FROM `categories` WHERE name = 'Specialty Cakes'));
