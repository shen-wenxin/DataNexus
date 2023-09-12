# 数据库表释义
按照要求只保留一个表格

\* 表示索引
## company_information
| 字段名 | 数据类型 | 描述 |约束条件|
| ------- | ------- | ------- |-------|
| company_id*  | INT  | 公司ID（主键）PRIMARY KEY|
| company_code*  | VARCHAR(50)  | 公司编码 | |
| company_name*  | VARCHAR(100)  | 公司全称 | |
| company_abbreviation  | VARCHAR(50)  | 公司简称  |可为空|
|registered_location| ENUM | 公司注册地（中国大陆，中国香港，境外）| |
| company_type  | VARCHAR(50)  | 公司类型  |
|unified_social_credit*|VARCHAR(50)|	统一社会信用编码|
|registered_address|	VARCHAR(200)|	公司详细注册地址|
|registered_phone|	VARCHAR(20)|	注册电话|
|company_email|	VARCHAR(100)|	公司邮箱|
|establishment_date|	DATE|成立时间|
|registered_capital|	DECIMAL(18,2)|	注册资本|
|legal_representative_name|	VARCHAR(100)|	法定代表人姓名|
|legal_representative_phone	|VARCHAR(20)	|法定代表人联系电话|
|legal_representative_id|	VARCHAR(50)	|法定代表人身份证号|
|industry	|VARCHAR(50)	|行业分类|
|business_scope|	VARCHAR(200)|	经营范围|
|is_verified_customer|	BOOLEAN|	是否为已认证客户|
|is_szse_member| BOOLEAN| 是否为深交所会员|
|szse_member_code| VARCHAR(50)| 深交所会员编码|
|szse_member_abbreviation| VARCHAR(50)| 深交所会员简称|
|customer_status|	VARCHAR(50)|	客户状态|
|country|	VARCHAR(50)|	所在国家|
|province	|VARCHAR(50)	|所在省份|
|city	|VARCHAR(50)	|所在城市|
|business_license_number	|VARCHAR(50)	|营业执照编号|
|business_license_expiry|	DATE|	营业执照有效期|
|primary_contact_name	|VARCHAR(100)	|主要联系人姓名|
|primary_contact_position	|VARCHAR(50)	|主要联系人职务|
|primary_contact_phone	|VARCHAR(20)	|主要联系人电话|
|primary_contact_email	|VARCHAR(100)	|主要联系人邮箱|



