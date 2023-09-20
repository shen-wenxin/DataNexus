package com.szsc.customermanagement.entity;

import java.time.LocalDateTime;

import lombok.*;
import com.szsc.customermanagement.enums.LocationEnum;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

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

}
