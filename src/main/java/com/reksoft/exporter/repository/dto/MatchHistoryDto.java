package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MatchHistoryDto {
    Integer id;
    Integer winnerId;
    Integer loserId;
    Integer tournamentId;
    LocalDateTime date;
    TeamDto winner;
    TeamDto loser;
    TournamentDto tournament;
}
