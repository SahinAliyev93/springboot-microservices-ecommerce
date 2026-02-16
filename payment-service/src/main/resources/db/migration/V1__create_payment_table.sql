CREATE TABLE payments (
                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          order_id VARCHAR(100) NOT NULL UNIQUE,
                          amount DECIMAL(10,2) NOT NULL,
                          status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
