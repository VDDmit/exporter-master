package com.reksoft.exporter.service;

import com.opencsv.CSVWriter;
import com.reksoft.exporter.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerCsvReportService {

    private final PlayerService playerService;

    public File generateReport(String filePath) throws IOException {
        List<Player> players = playerService.getPlayers();
        File file = new File(filePath);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeNext(getCsvHeader());

            for (Player player : players) {
                writer.writeNext(mapPlayerToCsvLine(player));
            }

            log.info("CSV report successfully written to {}", file.getAbsolutePath());
        } catch (IOException e) {
            log.error("Failed to write CSV report to {}", filePath, e);
            throw e;
        }

        return file;
    }

    private String[] getCsvHeader() {
        return new String[]{
                "ID", "Combined Name", "Nickname", "Country", "Team Name", "Full Name"
        };
    }

    private String[] mapPlayerToCsvLine(Player player) {
        return new String[]{
                String.valueOf(player.getId()),
                safe(player.getFullName()),
                safe(player.getNickname()),
                safe(Objects.toString(player.getCountry(), "")),
                safe(player.getTeamName()),
                safe(player.getFullNameWithNickname())
        };
    }

    private String safe(String value) {
        return value != null ? value : "";
    }
}