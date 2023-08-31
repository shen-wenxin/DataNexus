CREATE TABLE IF NOT EXISTS data_nexus_db.company_main_information (
    company_id INT PRIMARY KEY,
    company_code VARCHAR(50) NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    company_abbreviation VARCHAR(50),
    company_type VARCHAR(50),
    INDEX idx_company_code (company_code),
    INDEX idx_company_name (company_name)
);

CREATE TABLE IF NOT EXISTS data_nexus_db.company_detailed_information (
    company_id INT PRIMARY KEY,
    unified_social_credit VARCHAR(50),
    registered_address VARCHAR(200),
    registered_phone VARCHAR(20),
    company_email VARCHAR(100),
    establishment_date DATE,
    registered_capital DECIMAL(18,2),
    legal_representative_name VARCHAR(100),
    legal_representative_phone VARCHAR(20),
    legal_representative_id VARCHAR(50),
    industry VARCHAR(50),
    business_scope VARCHAR(200),
    is_verified_customer BOOLEAN,
    customer_status VARCHAR(50),
    country VARCHAR(50),
    province VARCHAR(50),
    city VARCHAR(50),
    business_license_number VARCHAR(50),
    business_license_expiry DATE,
    primary_contact_name VARCHAR(100),
    primary_contact_position VARCHAR(50),
    primary_contact_phone VARCHAR(20),
    primary_contact_email VARCHAR(100),
    FOREIGN KEY (company_id) REFERENCES data_nexus_db.company_main_information (company_id),
    INDEX idx_unified_social_credit (unified_social_credit)
);

CREATE TABLE IF NOT EXISTS data_nexus_db.registered_location (
  company_id INT PRIMARY KEY,
  location ENUM('chinaMainland', 'HongKong', 'overseas'),
  FOREIGN KEY (company_id) REFERENCES data_nexus_db.company_main_information (company_id),
  INDEX (company_id)
);

CREATE TABLE IF NOT EXISTS data_nexus_db.scope_information (
  company_id INT,
  scope VARCHAR(200),
  PRIMARY KEY (company_id),
  FOREIGN KEY (company_id) REFERENCES data_nexus_db.company_main_information (company_id),
  INDEX (company_id)
);

CREATE TABLE IF NOT EXISTS szse_membership (
  szse_member_id INT PRIMARY KEY,
  company_id INT,
  is_szse_member BOOLEAN,
  szse_member_code VARCHAR(50),
  szse_member_abbreviation VARCHAR(50),
  FOREIGN KEY (company_id) REFERENCES data_nexus_db.company_main_information (company_id),
  INDEX (is_szse_member)
);