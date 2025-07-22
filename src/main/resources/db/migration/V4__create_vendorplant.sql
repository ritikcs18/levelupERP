# USE tempdb;

CREATE TABLE vendor_plant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vendor_id INT NOT NULL,
    plant_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_vp_vendor FOREIGN KEY (vendor_id) REFERENCES vendor(id),
    CONSTRAINT fk_vp_plant FOREIGN KEY (plant_id) REFERENCES plant(id)
);