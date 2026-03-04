CREATE TABLE inventory (
                           id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           product_code VARCHAR(100) NOT NULL UNIQUE,
                           available_quantity INT NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
