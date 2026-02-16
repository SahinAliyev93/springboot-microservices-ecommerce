CREATE TABLE orders (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        product_code VARCHAR(100) NOT NULL UNIQUE,
                        quantity INT NOT NULL,
                        price DECIMAL(10,2) NOT NULL,
                        status VARCHAR(20) NOT NULL DEFAULT 'CREATED',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
