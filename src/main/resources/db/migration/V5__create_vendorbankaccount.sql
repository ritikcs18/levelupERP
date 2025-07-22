# USE tempdb;

CREATE TABLE vendor_bank_account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vendor_id INT NOT NULL,
    account_number VARCHAR(30) NOT NULL,
    ifsc_code VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_bank_vendor FOREIGN KEY (vendor_id) REFERENCES vendor(id)
);