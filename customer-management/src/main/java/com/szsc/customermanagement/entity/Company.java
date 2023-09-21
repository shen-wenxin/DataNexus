package com.szsc.customermanagement.entity;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import java.math.BigDecimal;

import lombok.*;
import com.szsc.customermanagement.enums.LocationEnum;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company {
    @ExcelIgnore // 忽略Excel导入导出
    private Long companyId; // 主键字段

    @ExcelProperty("公司代码")
    private String companyCode;

    @ExcelProperty("公司名称")
    private String companyName;

    @ExcelProperty("公司简称")
    private String companyAbbreviation;

    @ExcelProperty("公司类型")
    private String companyType;

    @ExcelProperty("注册地点")
    private String registeredLocation;

    @ExcelProperty("统一社会信用代码")
    private String unifiedSocialCredit;

    @ExcelProperty("注册地址")
    private String registeredAddress;

    @ExcelProperty("注册电话")
    private String registeredPhone;

    @ExcelProperty("公司邮箱")
    private String companyEmail;

    @ExcelProperty("成立日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String establishmentDate;

    @ExcelProperty("注册资本")
    private BigDecimal registeredCapital;

    @ExcelProperty("法定代表人姓名")
    private String legalRepresentativeName;

    @ExcelProperty("法定代表人电话")
    private String legalRepresentativePhone;

    @ExcelProperty("法定代表人身份证号")
    private String legalRepresentativeId;

    @ExcelProperty("行业")
    private String industry;

    @ExcelProperty("经营范围")
    private String businessScope;

    @ExcelProperty("是否已验证客户")
    private Boolean verifiedCustomer;

    @ExcelProperty("是否深交所会员")
    private Boolean szseMember;

    @ExcelProperty("深交所会员代码")
    private String szseMemberCode;

    @ExcelProperty("深交所会员简称")
    private String szseMemberAbbreviation;

    @ExcelProperty("客户状态")
    private String customerStatus;

    @ExcelProperty("所在国家")
    private String country;

    @ExcelProperty("所在省份")
    private String province;

    @ExcelProperty("所在城市")
    private String city;

    @ExcelProperty("营业执照号码")
    private String businessLicenseNumber;

    @ExcelProperty("营业执照到期日")
    private String businessLicenseExpiry;

    @ExcelProperty("主要联系人姓名")
    private String primaryContactName;

    @ExcelProperty("主要联系人职位")
    private String primaryContactPosition;

    @ExcelProperty("主要联系人电话")
    private String primaryContactPhone;

    @ExcelProperty("主要联系人邮箱")
    private String primaryContactEmail;
}
