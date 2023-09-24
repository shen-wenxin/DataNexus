package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.HistoryRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryRecordRepository {
    Page<HistoryRecord> listRecords(Pageable pageable);

    Page<HistoryRecord> listRecordByCode(Pageable pageable, String companyCode);

    HistoryRecord findById(int recordId);

    void save(HistoryRecord historyRecord);

    void deleteById(int recordId);

    Page<HistoryRecord> listRecordByType(Pageable pageable, String type);

    // 其他自定义方法
    // ...
}