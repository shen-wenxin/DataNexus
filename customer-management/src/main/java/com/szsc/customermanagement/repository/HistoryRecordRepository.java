package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.HistoryRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryRecordRepository {
    Page<HistoryRecord> findAll(Pageable pageable);

    Page<HistoryRecord> findByCode(Pageable pageable, String companyCode);

    HistoryRecord findById(int recordId);

    void save(HistoryRecord historyRecord);

    void deleteById(int recordId);

    Page<HistoryRecord> findByType(Pageable pageable, String type);
    
    // 其他自定义方法
    // ...
}