package com.szsc.customermanagement.repository.impl;

import com.szsc.customermanagement.config.AppConfig;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.entity.HistoryRecord;
import com.szsc.customermanagement.repository.HistoryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public class HistoryRecordRepositoryImpl implements HistoryRecordRepository {
    private final JdbcTemplate jdbcTemplate;

    @Value("${table.history.main}") // 从配置文件中读取表名
    private String tableName;

    @Autowired
    public HistoryRecordRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<HistoryRecord> listRecords(Pageable pageable) {
        String countSql = "SELECT COUNT(*) FROM " + tableName;
        Integer totalElements = jdbcTemplate.queryForObject(countSql, Integer.class);

        int totalElementsNonNull = totalElements != null ? totalElements : 0;

        String selectSql = "SELECT * FROM " + tableName + " ORDER BY record_id DESC LIMIT ? OFFSET ?";
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int offset = pageNumber * pageSize;
        List<HistoryRecord> historyRecords = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(HistoryRecord.class), pageSize, offset);
        return new PageImpl<>(historyRecords, pageable, totalElementsNonNull);
    }

    @Override
    public Page<HistoryRecord> listRecordByCode(Pageable pageable, String code) {
        String countSql = "SELECT COUNT(*) FROM " + tableName + " WHERE company_code = ?";
        Integer totalElements = jdbcTemplate.queryForObject(countSql, Integer.class, code);
        int totalElementsNonNull = totalElements != null ? totalElements : 0;
        String selectSql = "SELECT * FROM " + tableName + " WHERE company_code = ? ORDER BY record_id DESC LIMIT ? OFFSET ?";
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int offset = pageNumber * pageSize;

        List<HistoryRecord> records = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(HistoryRecord.class), code, pageSize, offset);

        return new PageImpl<>(records, pageable, totalElementsNonNull);
    }

    @Override
    public Page<HistoryRecord> listRecordByType(Pageable pageable, String type) {
        String operation = convertOperationType(type);

        String countSql = "SELECT COUNT(*) FROM " + tableName + " WHERE operation_type = ?";
        Integer totalElements = jdbcTemplate.queryForObject(countSql, Integer.class, operation);
        int totalElementsNonNull = totalElements != null ? totalElements : 0;

        String selectSql = "SELECT * FROM " + tableName + " WHERE operation_type = ? ORDER BY record_id DESC LIMIT ? OFFSET ?";
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int offset = pageNumber * pageSize;

        List<HistoryRecord> records = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(HistoryRecord.class), operation, pageSize, offset);

        return new PageImpl<>(records, pageable, totalElementsNonNull);
    }



    @Override
    public HistoryRecord findById(int recordId) {
        String selectSql = "SELECT * FROM " + tableName + " WHERE record_id = ?";
        List<HistoryRecord> historyRecords = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(HistoryRecord.class), recordId);
        if (historyRecords.isEmpty()) {
            return null; // 或者抛出自定义异常
        }
        return historyRecords.get(0);
    }

    @Override
    public void save(HistoryRecord historyRecord) {
        String insertSql = "INSERT INTO " + tableName + " (company_code, operation_type, operation_time, operator, modified_field, old_value, new_value, remark) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSql,
                historyRecord.getCompanyCode(),
                historyRecord.getOperationType(),
                historyRecord.getOperationTime(),
                historyRecord.getOperator(),
                historyRecord.getModifiedField(),
                historyRecord.getOldValue(),
                historyRecord.getNewValue(),
                historyRecord.getRemark());
    }

    @Override
    public void deleteById(int recordId) {
        String deleteSql = "DELETE FROM " + tableName + " WHERE record_id = ?";
        jdbcTemplate.update(deleteSql, recordId);
    }

    private String convertOperationType(String type) {

        switch (type) {
            case AppConfig.CODE_OPERATION_CREATE :
                return AppConfig.TYPE_OPERATION_CREATE;
            case AppConfig.CODE_OPERATION_UPDATE :
                return AppConfig.TYPE_OPERATION_UPDATE;
            case AppConfig.CODE_OPERATION_DELETE:
                return AppConfig.TYPE_OPERATION_DELETE;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }




}