package com.szsc.customermanagement.entity;

import java.time.LocalDateTime;

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

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getModifiedField() {
        return modifiedField;
    }

    public void setModifiedField(String modifiedField) {
        this.modifiedField = modifiedField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
