CREATE TABLE IF NOT EXISTS data_nexus_db.company_information (
  company_id INT PRIMARY KEY AUTO_INCREMENT,
  company_code VARCHAR(50) NOT NULL,
  company_name VARCHAR(100) NOT NULL,
  company_abbreviation VARCHAR(50),
  registered_location ENUM('中国大陆', '中国香港', '境外'),
  company_type VARCHAR(50),
  unified_social_credit VARCHAR(50),
  registered_address VARCHAR(200),
  registered_phone VARCHAR(20),
  company_email VARCHAR(100),
  establishment_date DATE,
  registered_capital DECIMAL(18, 2),
  legal_representative_name VARCHAR(100),
  legal_representative_phone VARCHAR(20),
  legal_representative_id VARCHAR(50),
  industry VARCHAR(50),
  business_scope VARCHAR(200),
  verified_customer BOOLEAN,
  szse_member BOOLEAN,
  szse_member_code VARCHAR(50),
  szse_member_abbreviation VARCHAR(50),
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
  INDEX idx_company_id (company_id),
  INDEX idx_company_code (company_code),
  INDEX idx_company_name (company_name),
  INDEX idx_unified_social_credit (unified_social_credit)
);

CREATE TABLE IF NOT EXISTS history_records (
  record_id INT PRIMARY KEY AUTO_INCREMENT,
  company_code VARCHAR(50) NOT NULL,
  operation_type VARCHAR(20),
  operation_time DATETIME,
  operator VARCHAR(50),
  modified_field VARCHAR(50),
  old_value VARCHAR(5000),
  new_value VARCHAR(5000),
  remark VARCHAR(255),
  INDEX idx_operation_type (operation_type),
  INDEX idx_company_code (company_code)
);





