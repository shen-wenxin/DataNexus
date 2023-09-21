package com.szsc.customermanagement.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import java.time.LocalDateTime;

import lombok.*;
import com.szsc.customermanagement.enums.LocationEnum;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class HistoryRecord {
    @Builder.Default
    @ExcelProperty("记录ID")
    private Integer recordId = null;

    @NonNull
    @ExcelProperty("公司代码")
    private String companyCode;

    @NonNull
    @ExcelProperty("操作类型")
    private String operationType;

    @ExcelProperty("操作时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationTime;

    @ExcelProperty("操作人")
    private String operator;

    @ExcelProperty("修改字段")
    private String modifiedField;

    @ExcelProperty("旧值")
    private String oldValue;

    @ExcelProperty("新值")
    private String newValue;

    @ExcelProperty("备注")
    private String remark;

}
