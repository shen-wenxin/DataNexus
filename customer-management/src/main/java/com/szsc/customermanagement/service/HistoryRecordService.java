package com.szsc.customermanagement.service;

import com.szsc.customermanagement.entity.HistoryRecord;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface HistoryRecordService {


    Page<HistoryRecord> findAll(Pageable pageable);

    byte[] exportHistoryRecordsAsExcel() throws IOException;

    Page<HistoryRecord> findByCode(Pageable pageable, String companyCode);

    Page<HistoryRecord> findByType(Pageable pageable, String type);
}