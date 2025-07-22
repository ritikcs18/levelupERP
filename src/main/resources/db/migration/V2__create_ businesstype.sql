# USE tempdb;

CREATE TABLE business_type (
    code VARCHAR(10) PRIMARY KEY,         -- E.g., WSS, TR, RT
    name VARCHAR(100),
    description TEXT
);