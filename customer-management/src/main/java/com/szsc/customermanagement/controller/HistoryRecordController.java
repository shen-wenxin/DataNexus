package com.szsc.customermanagement.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.entity.HistoryRecord;
import com.szsc.customermanagement.service.HistoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/history-records")
public class HistoryRecordController {

    private final HistoryRecordService historyRecordService;

    @Autowired
    public HistoryRecordController(HistoryRecordService historyRecordService) {
        this.historyRecordService = historyRecordService;
    }



    @GetMapping("/export")
    public void exportHistoryRecordsAsExcel(HttpServletResponse response) {
        try {
            List<HistoryRecord> historyRecordList = historyRecordService.exportHistoryRecords();

            // 设置响应头信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = "history_export.xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // 创建ExcelWriter对象
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), HistoryRecord.class).build();

            // 创建WriteSheet对象，并设置相关信息
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();

            // 写入数据到Excel
            excelWriter.write(historyRecordList, writeSheet);

            // 关闭ExcelWriter，释放资源
            excelWriter.finish();

            // 返回空的响应体，因为Excel数据已经通过response直接写出了

        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<HistoryRecord>> getPagedHistoryRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<HistoryRecord> historyRecords = historyRecordService.findAll(pageable);
        return new ResponseEntity<>(historyRecords, HttpStatus.OK);
    }

    @GetMapping("/paged_code")
    public ResponseEntity<Page<HistoryRecord>> getPagedHistoryRecordsByCode(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String code

    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<HistoryRecord> historyRecords = historyRecordService.listRecordByCode(pageable, code);
        return new ResponseEntity<>(historyRecords, HttpStatus.OK);
    }

    @GetMapping("/paged_type")
    public ResponseEntity<Page<HistoryRecord>> getPagedHistoryRecordsByType(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String type

    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<HistoryRecord> historyRecords = historyRecordService.listRecordByType(pageable, type);
        return new ResponseEntity<>(historyRecords, HttpStatus.OK);
    }
}