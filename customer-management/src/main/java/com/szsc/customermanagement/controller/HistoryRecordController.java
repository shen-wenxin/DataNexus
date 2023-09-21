package com.szsc.customermanagement.controller;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    public ResponseEntity<ByteArrayResource> exportHistoryRecordsAsExcel() {
        try {
            byte[] excelData = historyRecordService.exportHistoryRecordsAsExcel();

            System.out.println(excelData);

            // Create ByteArrayResource from excelData
            ByteArrayResource resource;
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                outputStream.write(excelData);
                resource = new ByteArrayResource(outputStream.toByteArray());
            }

            // Define response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "history_records.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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