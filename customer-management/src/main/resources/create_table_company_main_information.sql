CREATE TABLE IF NOT EXISTS data_nexus_db.company_main_information (
    company_id INT PRIMARY KEY,
    company_code VARCHAR(50) NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    company_abbreviation VARCHAR(50),
    company_type VARCHAR(50),
    INDEX idx_company_code (company_code),
    INDEX idx_company_name (company_name)
);