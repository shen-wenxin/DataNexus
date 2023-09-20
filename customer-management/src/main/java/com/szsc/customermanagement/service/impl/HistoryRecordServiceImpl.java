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

    public byte[] exportHistoryRecordsAsExcel() throws IOException {
        int pageSize = 100; // 每页记录数
        int page = 0; // 当前页数

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();

        boolean hasMoreData = true;
        while (hasMoreData) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            Page<HistoryRecord> historyPage = historyRecordRepository.listRecords(pageable);
            List<HistoryRecord> history = historyPage.getContent();

            System.out.println("Get in exportHistoryRecordsAsExcel\n\n===>");
            System.out.println(history.toString());

            if (history.isEmpty()) {
                break; // 没有更多数据，退出循环
            }

            Sheet sheet = workbook.createSheet("Companies - Page " + (page + 1));

            // 创建表头
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                "Id",
                "Company Code",
                "Operation Type",
                "Operation Time",
                "Operator",
                "Modified Field",
                "Old Value",
                "New Value",
                "Remark"
                
            };
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // 填充数据行
            int rowNum = 1;
            // 定义所需的时间格式
            // 定义所需的时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (HistoryRecord his : history) {

                String fomattedDateTime = his.getOperationTime().format(formatter);

                Row dataRow = sheet.createRow(rowNum++);


                dataRow.createCell(0).setCellValue(his.getRecordId());
                dataRow.createCell(1).setCellValue(his.getCompanyCode());
                dataRow.createCell(2).setCellValue(his.getOperationType());
                dataRow.createCell(3).setCellValue(fomattedDateTime);
                dataRow.createCell(4).setCellValue(his.getOperator());
                dataRow.createCell(5).setCellValue(his.getModifiedField());
                dataRow.createCell(6).setCellValue(his.getOldValue());
                dataRow.createCell(7).setCellValue(his.getNewValue());
                dataRow.createCell(8).setCellValue(his.getRemark());
            }
            page++;
            // 检查是否有更多页
            hasMoreData = historyPage.hasNext();
        }

        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
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