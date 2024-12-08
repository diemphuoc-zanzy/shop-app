-- Tạo database
CREATE DATABASE shopapp;

-- Tạo bảng users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT '',
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT DEFAULT 'ACTIVE'
);

-- Thêm cột role_id
ALTER TABLE users ADD COLUMN role_id INT;

-- Tạo bảng roles
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL DEFAULT 'GUEST',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT DEFAULT 'ACTIVE'
);

-- Thêm khóa ngoại role_id
ALTER TABLE users ADD CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles(id);

-- Tạo bảng tokens
CREATE TABLE tokens (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expiration_date TIMESTAMP,
    revoked BOOLEAN NOT NULL,
    expired BOOLEAN NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tạo bảng social_accounts
CREATE TABLE social_accounts (
    id SERIAL PRIMARY KEY,
    provider VARCHAR(20) NOT NULL,
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL,
    name VARCHAR(100) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tạo bảng categories
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL DEFAULT '',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT DEFAULT 'ACTIVE'
);

-- Tạo bảng products
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(350),
    price DOUBLE PRECISION NOT NULL CHECK(price >= 0),
    thumbnail VARCHAR(300) DEFAULT '',
    description TEXT DEFAULT '',
    category_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT DEFAULT 'ACTIVE',
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Tạo bảng product_images
CREATE TABLE product_images (
    id SERIAL PRIMARY KEY,
    product_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT DEFAULT 'ACTIVE',
    image_url VARCHAR(250),
    image_alt_text VARCHAR(150),
    is_primary BOOLEAN DEFAULT FALSE,
    image_size BIGINT,
    image_type VARCHAR(50),
    CONSTRAINT fk_product_images_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Tạo bảng orders
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    full_name VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    status TEXT,
    total_money DOUBLE PRECISION CHECK(total_money >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT DEFAULT 'ACTIVE',
    shipping_method VARCHAR(100),
    shipping_address VARCHAR(200),
    shipping_date DATE,
    tracking_number VARCHAR(100),
    tracking_method VARCHAR(100),
    active BOOLEAN
);

-- Sửa cột status chỉ nhận giá trị cụ thể
ALTER TABLE orders ADD CONSTRAINT chk_order_status CHECK (status IN ('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELED'));

-- Tạo bảng order_details
CREATE TABLE order_details (
    id SERIAL PRIMARY KEY,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    price DOUBLE PRECISION CHECK(price >= 0),
    number_of_products INT CHECK(number_of_products > 0),
    total_money DOUBLE PRECISION CHECK(total_money >= 0),
    color VARCHAR(20) DEFAULT ''
);
