# USE tempdb;

CREATE TABLE vendor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vendor_code VARCHAR(50) NOT NULL UNIQUE,  -- Format: VN-Pincode-CountryCode
    name VARCHAR(100) NOT NULL,
    address TEXT,
    city VARCHAR(50),
    region VARCHAR(50),
    country VARCHAR(50),
    business_type_code VARCHAR(10),
    govt_id VARCHAR(50),
    gst_number VARCHAR(20),
    is_active BOOLEAN DEFAULT TRUE,
    payment_currency VARCHAR(10),
    purchasing_group VARCHAR(100),
    onboarding_date DATE,
    exit_date DATE,
    isd_code VARCHAR(10),
    contact_number VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_vendor_business_type FOREIGN KEY (business_type_code)
        REFERENCES business_type(code)
);
