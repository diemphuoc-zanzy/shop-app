CREATE DATABASE shopapp;
USE shopapp;
-- Khách hàng khi muônns mua hàng => phải đăng ký tài khoản=> bằng users
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT '',
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    record_status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT 'trang thai hoat dong'
);

ALTER TABLE users ADD COLUMN role_id INT;

CREATE TABLE roles(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    record_status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT 'trang thai hoat dong'
);

ALTER TABLE roles MODIFY COLUMN name ENUM('ADMIN', 'SELLER', 'CUSTOMER', 'MODERATOR', 'GUEST') NOT NULL DEFAULT 'GUEST';
ALTER TABLE roles MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT;
ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id);

CREATE TABLE tokens(
    id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- hỗ trợ đăng nhập bằng Facebook hoặc Google

CREATE TABLE social_accounts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(20) NOT NULL COMMENT 'Tên Nhà social network',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL COMMENT 'Email tài khoản',
    name VARCHAR(100) NOT NULL COMMENT 'Tên người dùng',
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Bảng danh mục sản phẩm (Category)
CREATE TABLE categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Tên danh mục, vd: bánh quy',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    record_status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT 'trang thai hoat dong'
);

-- Bảng chứa sản phẩm (Product): "laptop macbook air 15 inch 2023"
CREATE TABLE products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(350) COMMENT 'Tên sản phẩm',
    price DOUBLE NOT NULL CHECK(price >= 0),
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT DEFAULT '',
    category_id INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    record_status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT 'trang thai hoat dong',
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE product_images(
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    record_status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT 'trang thai hoat dong',
    FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_product_images_product_id
        FOREIGN KEY (product_id) 
        REFERENCES products(id) ON DELETE CASCADE 
);

ALTER TABLE product_images ADD COLUMN image_url VARCHAR(250);
ALTER TABLE product_images ADD COLUMN image_alt_text VARCHAR(150);
ALTER TABLE product_images ADD COLUMN is_primary BOOLEAN DEFAULT FALSE;
ALTER TABLE product_images ADD COLUMN image_size BIGINT;
ALTER TABLE product_images ADD COLUMN image_type VARCHAR(50);


-- Đặt hàng orders
CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    full_name VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    status VARCHAR(20),
    total_money DOUBLE CHECK(total_money >= 0),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    record_status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT 'trang thai hoat dong'
);

ALTER TABLE orders ADD COLUMN shipping_method VARCHAR(100);
ALTER TABLE orders ADD COLUMN shipping_address VARCHAR(200);
ALTER TABLE orders ADD COLUMN shipping_date DATE;
ALTER TABLE orders ADD COLUMN tracking_number VARCHAR(100); 
ALTER TABLE orders ADD COLUMN tracking_method VARCHAR(100);

-- xoa don hang trang thai mem
ALTER TABLE orders ADD COLUMN active TINYINT(1);
-- trang thai don hang chi duoc phep nhan "mot so gia tri cu the"
ALTER TABLE orders MODIFY COLUMN status ENUM('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELED') COMMENT 'Trang thai don hang';

CREATE TABLE order_details(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    price DOUBLE CHECK(price >= 0),
    number_of_products INT CHECK(number_of_products > 0),
    total_money DOUBLE CHECK(total_money >= 0),
    color VARCHAR(20) DEFAULT ''
);

-- ALTER TABLE `products` CHANGE `price` `price` DOUBLE NOT NULL;