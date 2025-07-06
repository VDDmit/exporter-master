package com.reksoft.exporter.controller;

import com.reksoft.exporter.service.csv.PlayerCsvReportService;
import com.reksoft.exporter.service.csv.TeamCsvReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
@RequestMapping("/report")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportController {

    PlayerCsvReportService playerCsvReportService;
    TeamCsvReportService teamCsvReportService;
    Clock clock;

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    @GetMapping
    public String getReportPage() {
        return "report";
    }

    @GetMapping("/player/download")
    public ResponseEntity<Resource> downloadPlayerReport() throws IOException {
        return buildCsvResponse(
                "player_report",
                playerCsvReportService::generateReport
        );
    }

    @GetMapping("/team/download")
    public ResponseEntity<Resource> downloadTeamReport() throws IOException {
        return buildCsvResponse(
                "team_report",
                teamCsvReportService::generateReport
        );
    }

    private ResponseEntity<Resource> buildCsvResponse(String prefix, ReportGenerator generator) throws IOException {
        String timestamp = LocalDateTime.now(clock).format(TIMESTAMP_FORMAT);
        String filename = "%s_%s.csv".formatted(prefix, timestamp);
        File reportFile = generator.generate(TMP_DIR + File.separator + filename);

        if (!reportFile.exists()) {
            log.error("Generated file not found: {}", reportFile.getAbsolutePath());
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(reportFile);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentLength(Files.size(reportFile.toPath()))
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }

    @FunctionalInterface
    private interface ReportGenerator {
        File generate(String filePath) throws IOException;
    }
}