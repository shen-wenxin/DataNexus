package com.szsc.customermanagement.entity;

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
    private Integer recordId = null;
    @NonNull
    private String companyCode;
    @NonNull
    private String operationType;
    private LocalDateTime operationTime;
    private String operator;
    private String modifiedField;
    private String oldValue;
    private String newValue;
    private String remark;

}
