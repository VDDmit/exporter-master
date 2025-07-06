package com.reksoft.exporter.service.csv;

import com.opencsv.CSVWriter;
import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.service.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamCsvReportService {

    TeamService teamService;

    public File generateReport(String filePath) throws IOException {
        List<Team> teams = teamService.getAllTeams();
        File file = new File(filePath);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeNext(getCsvHeader());

            for (Team team : teams) {
                writer.writeNext(mapTeamToCsvLine(team));
            }

            log.info("Team CSV report successfully written to {}", file.getAbsolutePath());
        } catch (IOException e) {
            log.error("Failed to write team CSV report to {}", filePath, e);
            throw e;
        }

        return file;
    }

    private String[] getCsvHeader() {
        return new String[]{"ID", "Team Name", "Players"};
    }

    private String[] mapTeamToCsvLine(Team team) {
        return new String[]{
                String.valueOf(team.getId()),
                safe(team.getName()),
                safe(Objects.toString(team.getPlayers(), ""))
        };
    }

    private String safe(String value) {
        return value != null ? value : "";
    }
}