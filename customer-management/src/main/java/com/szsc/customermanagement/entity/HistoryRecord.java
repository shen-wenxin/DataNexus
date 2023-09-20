package com.szsc.customermanagement.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.szsc.customermanagement.enums.LocationEnum;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HistoryRecord {
    private int recordId;
    private String companyCode;
    private String operationType;
    private LocalDateTime operationTime;
    private String operator;
    private String modifiedField;
    private String oldValue;
    private String newValue;
    private String remark;


    @Override
    public String toString() {
        return "HistoryRecord{" +
                "recordId=" + recordId +
                ", companyCode='" + companyCode + '\'' +
                ", operationType='" + operationType + '\'' +
                ", operationTime=" + operationTime +
                ", operator='" + operator + '\'' +
                ", modifiedField='" + modifiedField + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
    
}
