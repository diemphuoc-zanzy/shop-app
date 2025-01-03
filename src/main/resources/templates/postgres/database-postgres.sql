-- CREATE
-- DATABASE shopapp;

CREATE TABLE users
(
    id                  SERIAL PRIMARY KEY,
    role_id             INT,
    full_name           VARCHAR(100)          DEFAULT '',
    phone_number        VARCHAR(10)  NOT NULL,
    address             VARCHAR(200)          DEFAULT '',
    password            VARCHAR(100) NOT NULL DEFAULT '',
    date_of_birth       DATE,
    facebook_account_id INT                   DEFAULT 0,
    google_account_id   INT                   DEFAULT 0,
    created_at          TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    record_status       TEXT                  DEFAULT 'ACTIVE'
);

CREATE TABLE roles
(
    id            SERIAL PRIMARY KEY,
    name          TEXT NOT NULL DEFAULT 'GUEST',
    created_at    TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT          DEFAULT 'ACTIVE'
);

CREATE TABLE permissions
(
    id            SERIAL PRIMARY KEY,
    role_id       INT,
    name          VARCHAR(100) NOT NULL UNIQUE,
    description   TEXT,
    resource      VARCHAR(100),
    method        VARCHAR(10) DEFAULT 'GET',
    action        VARCHAR(50)  NOT NULL,
    created_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT        DEFAULT 'ACTIVE'
);

CREATE TABLE tokens
(
    id              SERIAL PRIMARY KEY,
    user_id         INT,
    token           VARCHAR(255) UNIQUE NOT NULL,
    token_type      VARCHAR(50)         NOT NULL,
    expiration_date TIMESTAMP,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status   TEXT      DEFAULT 'ACTIVE'
);

CREATE TABLE social_accounts
(
    id          SERIAL PRIMARY KEY,
    user_id     INT,
    provider    VARCHAR(20)  NOT NULL,
    provider_id VARCHAR(50)  NOT NULL,
    email       VARCHAR(150) NOT NULL,
    name        VARCHAR(100) NOT NULL
);

CREATE TABLE categories
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL DEFAULT '',
    created_at    TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT                  DEFAULT 'ACTIVE'
);

CREATE TABLE products
(
    id            SERIAL PRIMARY KEY,
    category_id   INT,
    name          VARCHAR(350),
    price         DOUBLE PRECISION NOT NULL CHECK (price >= 0),
    thumbnail     VARCHAR(300) DEFAULT '',
    description   TEXT         DEFAULT '',
    created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    record_status TEXT         DEFAULT 'ACTIVE'
);

CREATE TABLE product_images
(
    id             SERIAL PRIMARY KEY,
    product_id     INT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_status  TEXT      DEFAULT 'ACTIVE',
    image_url      VARCHAR(250),
    image_alt_text VARCHAR(150),
    is_primary     BOOLEAN   DEFAULT FALSE,
    image_size     BIGINT,
    image_type     VARCHAR(50)
);

CREATE TABLE orders
(
    id               SERIAL PRIMARY KEY,
    user_id          INT,
    full_name        VARCHAR(100) DEFAULT '',
    email            VARCHAR(100) DEFAULT '',
    phone_number     VARCHAR(20)  NOT NULL,
    address          VARCHAR(200) NOT NULL,
    note             VARCHAR(100) DEFAULT '',
    status           TEXT,
    total_money      DOUBLE PRECISION CHECK (total_money >= 0),
    created_at       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    record_status    TEXT         DEFAULT 'ACTIVE',
    shipping_method  VARCHAR(100),
    shipping_address VARCHAR(200),
    shipping_date    DATE,
    tracking_number  VARCHAR(100),
    tracking_method  VARCHAR(100),
    active           BOOLEAN
);

CREATE TABLE order_details
(
    id                 SERIAL PRIMARY KEY,
    order_id           INT,
    product_id         INT,
    price              DOUBLE PRECISION CHECK (price >= 0),
    number_of_products INT CHECK (number_of_products > 0),
    total_money        DOUBLE PRECISION CHECK (total_money >= 0),
    color              VARCHAR(20) DEFAULT ''
);

ALTER TABLE users
    ADD CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE permissions
    ADD CONSTRAINT fk_permissions_roles FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE tokens
    ADD CONSTRAINT fk_tokens_users FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE social_accounts
    ADD CONSTRAINT fk_social_accounts_users FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products
    ADD CONSTRAINT fk_products_categories FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE product_images
    ADD CONSTRAINT fk_product_images_products FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE order_details
    ADD CONSTRAINT fk_order_details_orders FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_details
    ADD CONSTRAINT fk_order_details_products FOREIGN KEY (product_id) REFERENCES products (id);



ALTER TABLE permissions
    ADD CONSTRAINT chk_action CHECK (action IN ('VIEW', 'CREATE', 'UPDATE', 'DELETE', 'IMPORT', 'EXPORT', 'ALL', 'NONE'));

ALTER TABLE orders
    ADD CONSTRAINT chk_order_status CHECK (status IN ('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELED'));

-- ALTER TABLE users
--     DROP CONSTRAINT fk_users_roles;
-- ALTER TABLE permissions
--     DROP CONSTRAINT fk_permissions_roles;
-- ALTER TABLE tokens
--     DROP CONSTRAINT fk_tokens_users;
-- ALTER TABLE social_accounts
--     DROP CONSTRAINT fk_social_accounts_users;
-- ALTER TABLE products
--     DROP CONSTRAINT fk_products_categories;
-- ALTER TABLE product_images
--     DROP CONSTRAINT fk_product_images_products;
-- ALTER TABLE orders
--     DROP CONSTRAINT fk_orders_users;
-- ALTER TABLE order_details
--     DROP CONSTRAINT fk_order_details_orders;
-- ALTER TABLE order_details
--     DROP CONSTRAINT fk_order_details_products;
