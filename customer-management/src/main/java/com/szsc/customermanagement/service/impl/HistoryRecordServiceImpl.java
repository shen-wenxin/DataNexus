package com.szsc.customermanagement.service.impl;

import com.szsc.customermanagement.entity.HistoryRecord;
import com.szsc.customermanagement.repository.HistoryRecordRepository;
import com.szsc.customermanagement.service.HistoryRecordService;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class HistoryRecordServiceImpl implements HistoryRecordService {

    private final HistoryRecordRepository historyRecordRepository;

    @Autowired
    public HistoryRecordServiceImpl(HistoryRecordRepository historyRecordRepository) {
        this.historyRecordRepository = historyRecordRepository;
    }


    @Override
    public Page<HistoryRecord> findAll(Pageable pageable) {
        return historyRecordRepository.listRecords(pageable);
    }

    public List<HistoryRecord> exportHistoryRecords() throws IOException {
        int pageSize = 100; // 每页记录数
        int page = 0; // 当前页数

        List<HistoryRecord> allHistory = new ArrayList<>();



        boolean hasMoreData = true;
        while (hasMoreData) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            Page<HistoryRecord> historyPage = historyRecordRepository.listRecords(pageable);
            List<HistoryRecord> history = historyPage.getContent();


            if (history.isEmpty()) {
                break; // 没有更多数据，退出循环
            }

            allHistory.addAll(history);

            page++;
            // 检查是否有更多页
            hasMoreData = historyPage.hasNext();
        }


        return allHistory;
    }


    @Override
    public Page<HistoryRecord> listRecordByCode(Pageable pageable, String companyCode) {
        return historyRecordRepository.listRecordByCode(pageable, companyCode);
    }


    @Override
    public Page<HistoryRecord> listRecordByType(Pageable pageable, String type) {
        return historyRecordRepository.listRecordByType(pageable, type);
    }



}